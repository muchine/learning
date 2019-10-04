package coding.codewars.level3;

import java.util.*;
import java.util.stream.Collectors;

public class CheckAndMate {

    static final int MAX_TILES = 7;

    final Player owner;
    final Coordinates coordinates;

    CheckAndMate(PieceConfig[] pieces, int player) {
        this.owner = player == 0 ? Player.WHITE : Player.BLACK;
        coordinates = new Coordinates(Arrays.asList(pieces));
    }

    public Set<KingChecker> isCheck() {
        return coordinates.findCheckers(owner);
    }

    public boolean isMate() {
        Set<KingChecker> checkers = isCheck();
        return !canAvoidCheck() && !canCapture(checkers) && !canBlock(checkers);
    }

    private boolean canCapture(Set<KingChecker> checkers) {
        for (KingChecker checker : checkers) {
            Point checkerPoint = new Point(checker.pc.getX(), checker.pc.getY());
            for (Piece piece : coordinates.findPieces(owner)) {
                Point target = piece.capture(checkerPoint);
                if (target == null) continue;

                Coordinates c = piece.move(target);
                c.remove(checkerPoint);
                c.print();
                if (c.isCheck(owner)) return true;
            }
        }

        return false;
    }

    private boolean canBlock(Set<KingChecker> checkers) {
        for (KingChecker checker : checkers) {
            List<Point> route = new ArrayList<>();
            route.addAll(checker.route);

            for (Point p : route) {
                for (Piece piece : coordinates.findPieces(owner)) {
                    if (!piece.isMovable(p)) continue;

                    Coordinates c = piece.move(p);
                    c.print();
                    if (c.isCheck(owner)) return true;
                }
            }
        }

        return false;
    }

    private boolean canAvoidCheck() {
        King king = coordinates.findKing(owner);
        Point[] possible = new Point[] {
                king.position.up().left(), king.position.up(), king.position.up().right(),
                king.position.left(), king.position.right(),
                king.position.down().left(), king.position.down(), king.position.down().right()
        };

        for (Point point : possible) {
            if (point.isValid() && isCheckOnPossiblePosition(king, point)) return true;
        }

        return false;
    }

    private boolean isCheckOnPossiblePosition(King king, Point point) {
        if (!king.isMovable(point)) return false;

        Coordinates c = king.move(point);
        c.print();

        return c.isCheck(owner);
    }

    public static Set<PieceConfig> isCheck(final PieceConfig[] arrPieces, int player) {
        System.out.printf("piece: %s, player: %s\n", Arrays.asList(arrPieces), player);
        CheckAndMate cm = new CheckAndMate(arrPieces, player);
        return cm.isCheck().stream().map(c -> c.pc).collect(Collectors.toSet());
    }

    public static boolean isMate(final PieceConfig[] arrPieces, int player) {
        CheckAndMate cm = new CheckAndMate(arrPieces, player);
        return cm.isMate();
    }

    class KingChecker {
        PieceConfig pc;
        List<Point> route;

        public KingChecker(PieceConfig pc, List<Point> route) {
            this.pc = pc;
            this.route = route;
        }
    }

    class Coordinates {

        final Map<String, Piece> pieces = new HashMap<>();

        Coordinates(Iterable<PieceConfig> configs) {
            initialize(configs);
        }

        Coordinates(Coordinates coordinates) {
            this(coordinates.pieces.values().stream()
                    .map(p -> p.pieceConfig)
                    .collect(Collectors.toSet()));
        }

        public void print() {
            PieceConfig[] pcs = new PieceConfig[pieces.size()];
            pieces.values().stream().map(p -> p.pieceConfig).collect(Collectors.toList()).toArray(pcs);
            OutputBoard.print(pcs);
        }

        public boolean isCheck(Player player) {
            return findCheckers(player).isEmpty();
        }

        public Set<KingChecker> findCheckers(Player player) {
            Set<KingChecker> checks = new HashSet<>();

            King king = findKing(player);
            for (Piece piece : pieces.values()) {
                if (piece.owner == king.owner || piece == king) continue;

                List<Point> route = piece.findRoute(king.position);
                if (route != null) checks.add(new KingChecker(piece.pieceConfig, route));
            }

            return checks;
        }

        public List<Piece> findPieces(Player owner) {
            return pieces.values().stream()
                    .filter(p -> p.owner == owner && !(p instanceof King))
                    .collect(Collectors.toList());
        }

        public King findKing(Player owner) {
            for (Piece piece : pieces.values()) {
                if (piece.owner == owner && piece instanceof King) return (King) piece;
            }

            throw new IllegalStateException();
        }

        public void add(Piece piece) {
            Point position = piece.position;
            pieces.put(position.x + "" + position.y, piece);
        }

        public Piece get(int x, int y) {
            return pieces.get(x + "" + y);
        }

        public Piece get(Point p) {
            return get(p.x, p.y);
        }

        public void remove(int x, int y) {
            pieces.remove(x + "" + y);
        }

        public void remove(Point p) {
            remove(p.x, p.y);
        }

        private void initialize(Iterable<PieceConfig> pcs) {
            for (PieceConfig pc : pcs) {
                if (pc.getPiece().equals("king")) new King(pc, this);
                if (pc.getPiece().equals("queen")) new Queen(pc, this);
                if (pc.getPiece().equals("rook")) new Rook(pc, this);
                if (pc.getPiece().equals("bishop")) new Bishop(pc, this);
                if (pc.getPiece().equals("knight")) new Knight(pc, this);
                if (pc.getPiece().equals("pawn")) new Pawn(pc, this);
            }
        }
    }

    abstract class Piece {

        Coordinates coordinates;
        PieceConfig pieceConfig;

        Player owner;
        Point position;
        Point previous;

        Piece(PieceConfig pc, Coordinates coordinates) {
            this.coordinates = coordinates;
            this.pieceConfig = pc;

            this.owner = pc.getOwner() == 0 ? Player.WHITE : Player.BLACK;
            this.position = new Point(pc.getX(), pc.getY());

            int prevX = pc.hasPrevious() ? pc.getPrevX() : -1;
            int prevY = pc.hasPrevious() ? pc.getPrevY() : -1;
            this.previous = new Point(prevX, prevY);

            coordinates.add(this);
        }

        public Coordinates move(Point point) {
            if (!point.isValid()) throw new IllegalArgumentException();

            Coordinates c = new Coordinates(coordinates);
            if (isOpponentOccupied(point)) c.remove(point);
            c.remove(position);

            PieceConfig pc = new PieceConfig(pieceConfig.getPiece(), pieceConfig.getOwner(), point.x, point.y, pieceConfig.getX(), pieceConfig.getY());
            Piece moved = createMovedPiece(pc, c);
            System.out.printf("piece %s moved. %s -> %s\n", getName(), position, moved.position);

            return c;
        }

        public boolean hasPrevious() {
            return previous.isValid();
        }

        public boolean isEmpty(Point p) {
            return !isOccupied(p);
        }

        public boolean isOccupied(Point p) {
            return coordinates.get(p) != null;
        }

        public boolean isAllyOccupied(Point p) {
            Piece occupied = coordinates.get(p);
            return occupied != null && occupied.owner == this.owner;
        }

        public boolean isOpponentOccupied(Point p) {
            Piece occupied = coordinates.get(p);
            return occupied != null && occupied.owner != this.owner;
        }

        public Point capture(Point dest) {
            return isMovable(dest) ? dest : null;
        }

        public boolean isMovable(Point dest) {
            return findRoute(dest) != null;
        }

        public abstract List<Point> findRoute(Point dest);

        protected abstract Piece createMovedPiece(PieceConfig pc, Coordinates coordinates);

        public String getName() {
            return this.owner + " " + this.getClass().getSimpleName();
        }

        @Override
        public String toString() {
            return String.format("piece: %s, owner: %s, position: %s, previous: %s", getName(), owner, position, previous);
        }

    }

    abstract class StraightPiece extends Piece {

        StraightPiece(PieceConfig pc, Coordinates coordinates) {
            super(pc, coordinates);
        }

        @Override
        public final List<Point> findRoute(Point dest) {
            if (!isReachable(dest) || isAllyOccupied(dest)) return null;
            List<Point> path = new ArrayList<>();

            Point point = position;
            while (point.isValid()) {
                if (point.isLeftOf(dest)) point = point.right();
                if (point.isRightOf(dest)) point = point.left();
                if (point.isAbove(dest)) point = point.down();
                if (point.isBelow(dest)) point = point.up();

                path.add(point);
                if (point.equals(dest)) {
                    return path;
                } else {
                    if (isOccupied(point)) return null;
                }
            }

            return null;
        }

        protected abstract boolean isReachable(Point dest);

    }

    class King extends StraightPiece {

        King(PieceConfig pc, Coordinates coordinates) {
            super(pc, coordinates);
        }

        @Override
        protected Piece createMovedPiece(PieceConfig pc, Coordinates coordinates) {
            return new King(pc, coordinates);
        }

        @Override
        protected boolean isReachable(Point dest) {
            return position.isAdjacent(dest);
        }
    }

    class Rook extends StraightPiece {

        Rook(PieceConfig pc, Coordinates coordinates) {
            super(pc, coordinates);
        }

        @Override
        protected Piece createMovedPiece(PieceConfig pc, Coordinates coordinates) {
            return new Rook(pc, coordinates);
        }

        @Override
        protected boolean isReachable(Point dest) {
            return position.isInRow(dest) || position.isInColumn(dest);
        }

    }

    class Bishop extends StraightPiece {

        Bishop(PieceConfig pc, Coordinates coordinates) {
            super(pc, coordinates);
        }

        @Override
        protected Piece createMovedPiece(PieceConfig pc, Coordinates coordinates) {
            return new Bishop(pc, coordinates);
        }

        @Override
        protected boolean isReachable(Point dest) {
            return position.isDiagonal(dest);
        }
    }

    class Queen extends StraightPiece {

        Queen(PieceConfig pc, Coordinates coordinates) {
            super(pc, coordinates);
        }

        @Override
        protected Piece createMovedPiece(PieceConfig pc, Coordinates coordinates) {
            return new Queen(pc, coordinates);
        }

        @Override
        protected boolean isReachable(Point dest) {
            return position.isInRow(dest) || position.isInColumn(dest) || position.isDiagonal(dest);
        }
    }

    class Knight extends Piece {

        Knight(PieceConfig pc, Coordinates coordinates) {
            super(pc, coordinates);
        }

        @Override
        public final List<Point> findRoute(Point dest) {
            if (!isReachable(dest)) return null;

            List<Point> points = new ArrayList<>();
            points.add(dest);
            return points;
        }

        @Override
        protected Piece createMovedPiece(PieceConfig pc, Coordinates coordinates) {
            return new Knight(pc, coordinates);
        }

        private boolean isReachable(Point dest) {
            if (Math.abs(position.x - dest.x) == 2 && Math.abs(position.y - dest.y) == 1) return true;
            if (Math.abs(position.x - dest.x) == 1 && Math.abs(position.y - dest.y) == 2) return true;

            return false;
        }
    }

    class Pawn extends Piece {

        private final Point forward;

        Pawn(PieceConfig pc, Coordinates coordinates) {
            super(pc, coordinates);
            this.forward = forward(position);
        }

        @Override
        protected Piece createMovedPiece(PieceConfig pc, Coordinates coordinates) {
            return new Pawn(pc, coordinates);
        }

        @Override
        public Point capture(Point dest) {
            Point captured = super.capture(dest);
            if (captured != null) return captured;

            // see if en passant
            if (!position.isLeftOf(dest) && !position.isRightOf(dest)) return null;
            if (!isOpponentOccupied(dest)) return null;

            Piece occupied = coordinates.get(dest);
            if (!(occupied instanceof Pawn)) return null;

            Pawn occupiedPawn = (Pawn) occupied;
            if (!occupiedPawn.isFirstMove()) return null;

            Point target = position.isLeftOf(dest) ? forward.right() : forward.left();
            if (isOccupied(target)) return null;

            return target;
        }

        @Override
        public List<Point> findRoute(Point dest) {
            Point point;

            if (forward.equals(dest) && isEmpty(dest)) return createMovablePoint(dest);
            if (isDiagonal(dest) && isOpponentOccupied(dest)) return createMovablePoint(dest);
            if (!hasPrevious()) {
                point = forward(forward);
                if (point.equals(dest) && isEmpty(point)) return createMovablePoint(point);
            }

            return null;
        }

        private boolean isDiagonal(Point point) {
            return forward.left().equals(point) || forward.right().equals(point);
        }

        private Point forward(Point point) {
            return owner == Player.WHITE ? point.up() : point.down();
        }

        private List<Point> createMovablePoint(Point p) {
            List<Point> points = new ArrayList<>();
            points.add(p);
            return points;
        }

        private boolean isFirstMove() {
            return previous.isValid() && forward(forward(previous)).equals(position);
        }
    }

    static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public boolean isValid() {
            return 0 <= x && x <= MAX_TILES && 0 <= y && y <= MAX_TILES;
        }

        public Point left() {
            return new Point(x - 1, y);
        }

        public Point right() {
            return new Point(x + 1, y);
        }

        public Point up() {
            return new Point(x, y - 1);
        }

        public Point down() {
            return new Point(x, y + 1);
        }

        public boolean isLeftOf(Point point) {
            return x < point.x;
        }

        public boolean isRightOf(Point point) {
            return x > point.x;
        }

        public boolean isAbove(Point point) {
            return y < point.y;
        }

        public boolean isBelow(Point point) {
            return y > point.y;
        }

        public boolean isInRow(Point point) {
            return x == point.x;
        }

        public boolean isInColumn(Point point) {
            return y == point.y;
        }

        public boolean isDiagonal(Point point) {
            return Math.abs(x - point.x) == Math.abs(y - point.y);
        }

        public boolean isAdjacent(Point point) {
            return Math.abs(x - point.x) <= 1 && Math.abs(y - point.y) <= 1;
        }

        @Override
        public boolean equals(Object obj) {
            Point o = (Point) obj;
            return this.x == o.x && this.y == o.y;
        }

        @Override
        public String toString() {
            return String.format("(%s, %s)", x, y);
        }
    }

    enum Player {
        WHITE, BLACK
    }
}

class PieceConfig {

    private String piece;

    private int owner, x, y, prevX, prevY;

    public PieceConfig(final String piece, final int owner, final int x, final int y, final int prevX, final int prevY) {
        this.piece = piece;
        this.owner = owner;
        this.x = x;
        this.y = y;
        this.prevX = prevX;
        this.prevY = prevY;
    }

    public PieceConfig(final String piece, final int owner, final int x, final int y) {
        this(piece, owner, x, y, -1, -1);
    }

    public String getPiece() {
        return piece;
    }

    public int getOwner() {
        return owner;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean hasPrevious() {
        return prevX >= 0 && prevY >= 0;
    }

    public int getPrevX() {
        // will throw a RuntimeException if invoked for an object that do not have informations about its previous move.
        if (prevX == -1) throw new RuntimeException();
        return prevX;
    }

    public int getPrevY() {
        // will throw a RuntimeException if invoked for an object that do not have informations about its previous move.
        if (prevY == -1) throw new RuntimeException();
        return prevY;
    }

    @Override
    public String toString() {
        //return string formated as :"piece: king, owner: 0, x: 0, y: 4" (plus prevX and prevY if needed)
        return String.format("piece: %s, owner: %s, x: %s, y: %s, prevX: %s, prevY: %s", piece, owner, x, y, prevX, prevY);
    }

}

class OutputBoard {
    public static void print(PieceConfig[] game) {
        final Map<String, PieceConfig> coordinates = new HashMap<>();
        for (PieceConfig pc : game) {
            coordinates.put(pc.getX() + "" + pc.getY(), pc);
        }

        System.out.printf("   ");
        for (int i = 0; i <= 7; i++) System.out.printf("  " + i + "  ");
        System.out.printf("\n");

        for (int y = 0; y <= 7; y++) {
            System.out.printf(y + " |");
            for (int x = 0; x <= 7; x++) {
                PieceConfig pc = coordinates.get(x + "" + y);
                if (pc != null) {
                    System.out.printf(" " + code(pc.getPiece()) + pc.getOwner() + " |");
                } else {
                    System.out.printf("    |");
                }
            }
            System.out.printf("\n");
        }
    }

    private static String code(String piece) {
        if (piece.equals("queen")) return "Q";
        if (piece.equals("rook")) return "R";
        if (piece.equals("bishop")) return "B";
        if (piece.equals("knight")) return "N";
        if (piece.equals("pawn")) return "P";
        if (piece.equals("king")) return "K";

        throw new IllegalArgumentException();
    }
}