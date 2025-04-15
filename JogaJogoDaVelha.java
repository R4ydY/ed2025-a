import java.util.Random;
import java.util.Scanner;

public class JogaJogoDaVelha {
    private static char[][] tabuleiro;
    private static char jogadorAtual = 'X';
    private static final Random random = new Random();
    private static Scanner scanner = new Scanner(System.in);
    private static int tamanhoTabuleiro;

    public static void main(String[] args) {
        System.out.println("Jogo da Velha Automático Dinâmico");
        
        solicitarTamanhoTabuleiro();
        
        boolean continuarJogando = true;
        while (continuarJogando) {
            inicializarTabuleiro();
            jogarPartidaAutomatica();
            
            exibirTabuleiro();
            if (verificarVencedor()) {
                System.out.println("Jogador " + jogadorAtual + " venceu!");
            } else {
                System.out.println("O jogo deu 0");
            }
            
            System.out.println("digite a opção desejada");
            System.out.println("1-jogar novamente ");
            System.out.println("2-escolher outro tamanho de um tabuleiro ");
            System.out.println("3-sair");
            
            int opcao = scanner.nextInt();
            switch (opcao) {
                case 1:
                    jogadorAtual = 'X';
                    break;
                case 2:
                    solicitarTamanhoTabuleiro();
                    jogadorAtual = 'X';
                    break;
                case 3:
                    continuarJogando = false;
                    break;
                default:
            }
        }   
        
        scanner.close();
        System.out.println("Fim do jogo!");
    }

    private static void solicitarTamanhoTabuleiro() {
        System.out.print("\nDigite o tamanho do tabuleiro: ");
        tamanhoTabuleiro = scanner.nextInt();
        tabuleiro = new char[tamanhoTabuleiro][tamanhoTabuleiro];
    }

    private static void inicializarTabuleiro() {
        for (int i = 0; i < tamanhoTabuleiro; i++) {
            for (int j = 0; j < tamanhoTabuleiro; j++) {
                tabuleiro[i][j] = '-';
            }
        }
    }

    private static void jogarPartidaAutomatica() {
        while (true) {
            int linha, coluna;
            do {
                linha = random.nextInt(tamanhoTabuleiro);
                coluna = random.nextInt(tamanhoTabuleiro);
            } while (tabuleiro[linha][coluna] != '-');
            
            tabuleiro[linha][coluna] = jogadorAtual;
            
            if (verificarVencedor() || verificarEmpate()) {
                break;
            }
            
            alternarJogador();
        }
    }

    private static boolean verificarVencedor() {
        for (int i = 0; i < tamanhoTabuleiro; i++) {
            boolean venceu = true;
            for (int j = 0; j < tamanhoTabuleiro; j++) {
                if (tabuleiro[i][j] != jogadorAtual) {
                    venceu = false;
                    break;
                }
            }
            if (venceu) return true;
        }
        
        for (int j = 0; j < tamanhoTabuleiro; j++) {
            boolean venceu = true;
            for (int i = 0; i < tamanhoTabuleiro; i++) {
                if (tabuleiro[i][j] != jogadorAtual) {
                    venceu = false;
                    break;
                }
            }
            if (venceu) return true;
        }
        
        boolean venceu = true;
        for (int i = 0; i < tamanhoTabuleiro; i++) {
            if (tabuleiro[i][i] != jogadorAtual) {
                venceu = false;
                break;
            }
        }
        if (venceu) return true;
        
        venceu = true;
        for (int i = 0; i < tamanhoTabuleiro; i++) {
            if (tabuleiro[i][tamanhoTabuleiro-1-i] != jogadorAtual) {
                venceu = false;
                break;
            }
        }
        return venceu;
    }

    private static boolean verificarEmpate() {
        for (int i = 0; i < tamanhoTabuleiro; i++) {
            for (int j = 0; j < tamanhoTabuleiro; j++) {
                if (tabuleiro[i][j] == '-') {
                    return false;
                }
            }
        }
        return !verificarVencedor();
    }

    private static void alternarJogador() {
        jogadorAtual = (jogadorAtual == 'X') ? 'O' : 'X';
    }

    private static void exibirTabuleiro() {
        System.out.println();
        for (int i = 0; i < tamanhoTabuleiro; i++) {
            for (int j = 0; j < tamanhoTabuleiro; j++) {
                System.out.print(" " + tabuleiro[i][j]);
                if (j < tamanhoTabuleiro-1) {
                    System.out.print(" |");
                }
            }
            System.out.println();
                if (i < tamanhoTabuleiro-1) {
                System.out.print("---");
                for (int j = 1; j < tamanhoTabuleiro; j++) {
                    System.out.print("----");
                }
                System.out.println();
            }
        }
        System.out.println();
    }
}