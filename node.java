import java.util.Scanner;

class Nodo {
    char valor;
    Nodo anterior;
    Nodo siguiente;

    Nodo() {
    }

    Nodo(char valor) {
        this.valor = valor;
    }
}

class ListaDoblementeEnlazada {
    Nodo head;
    Nodo tail;
    Nodo cursor;

    ListaDoblementeEnlazada() {
        head = new Nodo(); // Nodo fantasma al principio
        tail = new Nodo(); // Nodo fantasma al final
        head.siguiente = tail;
        tail.anterior = head;
        cursor = head; // El cursor comienza en el nodo fantasma al principio
    }

    void insertar(char valor) {
        Nodo nuevoNodo = new Nodo(valor);
        nuevoNodo.anterior = cursor;
        nuevoNodo.siguiente = cursor.siguiente;
        cursor.siguiente.anterior = nuevoNodo;
        cursor.siguiente = nuevoNodo;
        cursor = nuevoNodo;
    }

    void eliminar() {
        if (cursor != head) {
            cursor.anterior.siguiente = cursor.siguiente;
            cursor.siguiente.anterior = cursor.anterior;
            cursor = cursor.anterior;
        }
    }

    void moverCursorIzquierda() {
        if (cursor != head) {
            cursor = cursor.anterior;
        }
    }

    void moverCursorDerecha() {
        if (cursor.siguiente != tail) {
            cursor = cursor.siguiente;
        }
    }

    String obtenerTexto() {
        StringBuilder texto = new StringBuilder();
        Nodo actual = head.siguiente;
        while (actual != tail) {
            texto.append(actual.valor);
            actual = actual.siguiente;
        }
        return texto.toString();
    }
}

class EditorTexto {
    ListaDoblementeEnlazada lista;

    EditorTexto() {
        lista = new ListaDoblementeEnlazada();
    }

    void insertar(char caracter) {
        lista.insertar(caracter);
    }

    void eliminar() {
        lista.eliminar();
    }

    void moverCursorIzquierda() {
        lista.moverCursorIzquierda();
    }

    void moverCursorDerecha() {
        lista.moverCursorDerecha();
    }

    void mostrarTexto() {
        System.out.println(lista.obtenerTexto());
    }

    public static void main(String[] args) {
        EditorTexto editor = new EditorTexto();
        Scanner scanner = new Scanner(System.in);

        System.out.println("----------------------------");
        System.out.println("   Editor de texto simple   ");
        System.out.println("----------------------------");
        System.out.println("");
        System.out.println("Comandos disponibles:");
        System.out.println("");
        System.out.println(
                "1. Para insertar un caracter en la posición del cursor escriba: insertar <caracter que desea ingresar>");
        System.out.println("2. Para eliminar el caracter en la posición del cursor escriba: eliminar");
        System.out.println("3. Para mover el cursor a la izquierda escriba: izquierda");
        System.out.println("4. Para mover el cursor a la derecha escriba: derecha");
        System.out.println("5. Para mostrar el texto actual escriba: mostrar");
        System.out.println("6. Para terminar el programa escriba: salir");
        System.out.println("");

        while (true) {
            System.out.print("Comando: ");
            String[] comando = scanner.nextLine().trim().split("\\s+");
            if (comando.length == 0) {
                continue;
            }

            String accion = comando[0].toLowerCase();

            switch (accion) {
                case "insertar":
                    if (comando.length == 2) {
                        char caracter = comando[1].charAt(0);
                        editor.insertar(caracter);
                    } else {
                        System.out.println("Comando no reconocido. Intente de nuevo.");
                    }
                    break;
                case "eliminar":
                    editor.eliminar();
                    break;
                case "izquierda":
                    editor.moverCursorIzquierda();
                    break;
                case "derecha":
                    editor.moverCursorDerecha();
                    break;
                case "mostrar":
                    editor.mostrarTexto();
                    break;
                case "salir":
                    System.out.println("Gracias por usar este programa...");
                    System.out.println("");
                    scanner.close();
                    return;
                default:
                    System.out.println("Comando no reconocido. Intente de nuevo.");
                    break;
            }
        }
    }
}
