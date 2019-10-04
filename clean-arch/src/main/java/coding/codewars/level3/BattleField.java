package coding.codewars.level3;

import java.util.*;

public class BattleField {

    public static boolean fieldValidator(int[][] field) {
        Map<Integer, Integer> ships = new HashMap<>();
        ships.put(4, 0);
        ships.put(3, 0);
        ships.put(2, 0);
        ships.put(1, 0);

        int count = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (field[i][j] == 0) continue;

                // find a ship
                Ship ship = findShip(field, i, j);
                System.out.println("ship: " + ship);
                if (!validate(field, ship)) return false;

                int shipSize = ship.nodes.size();
                if (shipSize > 4) return false;

                count += shipSize;
                ships.put(shipSize, ships.get(shipSize) + 1);

                for (Node n : ship.nodes) {
                    field[n.x][n.y] = 0;
                }
            }
        }

        return count == 20 && ships.get(4) == 1 && ships.get(3) == 2 && ships.get(2) == 3 && ships.get(1) == 4;
    }

    private static Ship findShip(int[][] field, int i, int j) {
        List<Node> ship = new ArrayList<>();
        ship.add(new Node(i, j));

        int y = j + 1;
        while (y <= 9 && field[i][y] == 1) {
            ship.add(new Node(i, y));
            y++;
        }

        if (ship.size() > 1) {
            return new Ship(ship);
        }

        int x = i + 1;
        while (x <= 9 && field[x][j] == 1) {
            ship.add(new Node(x, j));
            x++;
        }

        return new Ship(ship);
    }

    private static boolean validate(int[][] field, Ship ship) {
        for (Node node : ship.nodes) {
            int x = node.x;
            int y = node.y;

            if (!validateNode(field, ship, x - 1, y - 1)) return false;
            if (!validateNode(field, ship, x - 1, y )) return false;
            if (!validateNode(field, ship, x - 1, y + 1)) return false;
            if (!validateNode(field, ship, x, y - 1)) return false;
            if (!validateNode(field, ship, x, y + 1)) return false;
            if (!validateNode(field, ship, x + 1, y - 1)) return false;
            if (!validateNode(field, ship, x + 1, y)) return false;
            if (!validateNode(field, ship, x + 1, y + 1)) return false;
        }

        return true;
    }

    private static boolean validateNode(int[][] field, Ship ship, int x, int y) {
        if (x < 0 || y < 0 || x > 9 || y > 9) return true;
        return ship.contains(x, y) || field[x][y] == 0;
    }

    public static class Ship {

        List<Node> nodes;

        public Ship(List<Node> nodes) {
            this.nodes = nodes;
        }

        public boolean contains(int i, int j) {
            for (Node node : nodes) {
                if (node.x == i && node.y == j) return true;
            }

            return false;
        }
    }

    public static class Node {
        final int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}
