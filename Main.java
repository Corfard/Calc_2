import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner vvod = new Scanner(System.in);
        while (true) {
            System.out.println("Введите пример:");
            String text = vvod.nextLine();
            Element element = new Element();
            element.setText(text);
            if (!element.getProverka()){
                System.out.println("ОШИБКА!");
                break;
            }
            System.out.println("Ответ:");
            System.out.println(element.getOtvet());
        }
    }
}
class Element{
    public void setText(String text){
        this.text = text + " ";
        primer = this.text.split("\"");
        if (primer.length == 3 || primer.length == 5){
            kovichki();
            opredelenieZnaka();
            pervoe();
            vtoroe();
        }else try {
            throw new Exception();
        } catch (Exception e) {
            proverka = false;
        }
    }

    private boolean proverka = true;
    private String text;
    private Plus plus;
    private Minus minus;
    private Umnojenie umnojenie;
    private Delenie delenie;
    private String[] znaki = {"+", "-", "*", "/"};
    private String[] primer;
    private int znakID = -1;
    private int c;
    private String a;
    private String b;

    public boolean getProverka(){
        return proverka;
    }

    public void kovichki(){
        if (primer[0].trim().length() != 0){
            try {
                throw new Exception();
            } catch (Exception e) {
                proverka = false;
            }
        }
        if (primer.length == 5 && primer[4].trim().length() != 0){
            try {
                throw new Exception();
            } catch (Exception e) {
                proverka = false;
            }
        }
    }

    public void pervoe(){
        if (primer[1].length() > 10){
            try {
                throw new Exception();
            } catch (Exception e) {
                proverka = false;
            }
        }else a = primer[1];
    }

    public void vtoroe(){
        if (primer.length == 5 && (znakID == 0 || znakID == 1)){
            if (primer[3].length() > 10){
                try {
                    throw new Exception();
                } catch (Exception e) {
                    proverka = false;
                }
            }else b = primer[3];
        }

        else if (primer.length == 3 && (znakID == 2 || znakID == 3)){
            try {
                c = Integer.valueOf(primer[2].trim().substring(1).trim());
            } catch (Exception e) {
                proverka = false;
            }
            if (c > 10 || c < 1){
                try {
                    throw new Exception();
                } catch (Exception e) {
                    proverka = false;
                }
            }else b = primer[2].trim().substring(1).trim();
        }

        else try {
                throw new Exception();
            } catch (Exception e) {
                proverka = false;
            }
    }

    public void opredelenieZnaka(){
        for (int i=0; i<znaki.length; i++){
            if (primer[2].contains(znaki[i])){
                znakID = i;
                break;
            }
        }

        if (znakID == -1){
            try {
                throw new Exception();
            } catch (Exception e) {
                proverka = false;
            }
        }

        if (primer.length == 5 && primer[2].trim().length() != 1){
            try {
                throw new Exception();
            } catch (Exception e) {
                proverka = false;
            }
        }
    }

    public String getOtvet(){
            switch (znakID){
                case 0 -> {plus = new Plus(a, b); return plus.getOtvet();}
                case 1 -> {minus = new Minus(a, b); return minus.getOtvet();}
                case 2 -> {umnojenie = new Umnojenie(a, b); return umnojenie.getOtvet();}
                case 3 -> {delenie = new Delenie(a, b); return delenie.getOtvet();}
            }
            return "";
    }
}
class Plus{
    Plus(String a, String b){
        this.a = a;
        this.b = b;
    }
    private String a;
    private String b;

    public String getOtvet() {
        return "\"" + a+b + "\"";
    }
}
class Minus{
    Minus(String a, String b){
        this.a = a;
        this.b = b;
    }
    private String a;
    private String b;

    public String getOtvet() {
        return "\"" + a.replace(b, "") + "\"";
    }
}
class Umnojenie{
    Umnojenie(String a, String b){
        this.a = a;
        this.b = Integer.valueOf(b);
        abc();

    }
    private String a;
    private int b;
    private String otvet = "";
    private char[] simvol;

    public void abc(){
        for (int i=0; i<b; i++){
            otvet += a;
        }
    }

    public String getOtvet() {
        simvol = otvet.toCharArray();
        if (simvol.length<41){
            return "\"" + otvet+ "\"";
        }else
            otvet = "";
            for (int i=0; i<40; i++){
                otvet += simvol[i];
        }return "\"" + otvet+ "...\"";
    }
}
class Delenie{
    Delenie(String a, String b){
        this.a = a;
        this.b = Integer.valueOf(b);
        simvol = a.toCharArray();
        abc();
    }
    
    private String a;
    private int b;
    private char[] simvol;
    private int c;
    private String otvet = "";

    public void abc(){
        c = simvol.length / b;
        for (int i=0; i<c; i++){
            otvet += simvol[i];
        }
    }
    public String getOtvet() {
        return "\"" + otvet+ "\"";
    }
}