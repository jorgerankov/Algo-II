package aed;

class Funciones {
    int cuadrado(int x) {
        return x*x;
    }

    double distancia(double x, double y) {
        return Math.sqrt((x*x) + (y*y));
    }

    boolean esPar(int n) {
        if (n%2 == 0) {
            return true;
        }
        return false;
    }

    boolean esBisiesto(int n) {
        if ((n%4 == 0 && n%100 != 0) || n%400 == 0) {
            return true;
        }
        return false;
    }

    int factorialIterativo(int n) {
        int res = 1;

        if (n == 0) {
            return 1;
        }
        for (int i = 1; i <= n; i++) {
            res *= i;
        }
        return res;
    }

    int factorialRecursivo(int n) {
        int res = 1;
        if (n == 0) {
            return 1;
        }
        while(n > 0) {
            res *= n;
            n--;
        }
        return res;
    }

    boolean esPrimo(int n) {
        int res = 0;

        for(int i = 1; i <= n; i++){
            if (n%i == 0){
                res += 1;
            }
        }
        if (res == 2){
            return true;
        }
        else{
            return false;
        }
    }

    int sumatoria(int[] numeros) {
        int res = 0;
        for (int i = 0; i < numeros.length; i++) {
            res += numeros[i];
        }
        return res;
    }

    int busqueda(int[] numeros, int buscado) {
        int posicion = 0;
        for (int i = 0; i < numeros.length; i++){
            if (buscado == numeros[i]){
                return posicion;
            }
            else{
                posicion++;
            }
        }
        return posicion;
    }

    boolean tienePrimo(int[] numeros) {
        boolean res = false;

        for (int i = 0; i < numeros.length; i++){
            if(esPrimo(numeros[i]) != res){
                res = true;
                return res;
            }
        }
        return res;
    }

    boolean todosPares(int[] numeros) {
        for (int i = 0; i < numeros.length; i++){
            if (numeros[i]%2 != 0){
                return false;
            }
        }
        return true;
    }

    boolean esPrefijo(String s1, String s2) {
        int contador_s1 = 0;

        if (s1.length() > s2.length()){
            return false;
        }
        for (int i = 0; i < s1.length(); i++){
            if (s1.charAt(contador_s1) != s2.charAt(i)){
                return false;
            }
            contador_s1++;
            if (contador_s1 > s1.length()){
                return false;
            }
        }
        return true;
        }

    boolean esSufijo(String s1, String s2) {

        if (s1.length() > s2.length()){
            return false;
        }

        for (int i = s1.length() -1; i >= 0; i--){
            // contador_s2 me permite ir viendo los chars de s2 a partir de donde comenzaria s1
            int contador_s2 = s2.length() - s1.length() + i;
            if (s1.charAt(i) != s2.charAt(contador_s2)){
                return false;
            }
        }
        return true;
    }
}

