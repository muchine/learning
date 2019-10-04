public enum Flash {

    AUTO, ON, OFF;

    public Flash getNext() {
        Flash[] flashs = Flash.values();
        return flashs[(ordinal() + 1) % flashs.length];
    }

    public static void main(String[] args) {
        System.out.println(AUTO.getNext());
        System.out.println(ON.getNext());
        System.out.println(OFF.getNext());
    }

}
