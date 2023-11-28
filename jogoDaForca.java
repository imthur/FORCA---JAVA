import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;

public class JogoDaForca {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        do {
            int erros = 0;

            String[] profissoes = {"ENGENHEIRO", "MEDICO", "PROFESSOR", "DESENVOLVEDOR", "ENFERMEIRO", "ADVOCACIA", "ARQUITETO", "ELETRICISTA", "PSICOLOGO", "POLICIAL"};
            String[] frutas = {"MANGA", "BANANA", "UVA", "ABACAXI", "MELANCIA", "LARANJA", "MACA", "PERA", "KIWI", "MORANGO"};

            String[] palavras = new String[15];
            System.arraycopy(profissoes, 0, palavras, 0, 10);
            System.arraycopy(frutas, 0, palavras, 10, 5);
            Random random = new Random();
            String palavraSecreta = palavras[random.nextInt(palavras.length)];

            boolean ehFruta = Arrays.asList(frutas).contains(palavraSecreta);
            boolean ehProfissao = Arrays.asList(profissoes).contains(palavraSecreta);

            int tamanhoPalavra = palavraSecreta.length();
            char[] palavraAdivinhada = new char[tamanhoPalavra];
            for (int i = 0; i < tamanhoPalavra; i++) {
                palavraAdivinhada[i] = '_';
            }

            int tentativasMaximas = 6;
            int tentativasRestantes = tentativasMaximas;

            boolean[] letrasDigitadas = new boolean[26];

            while (tentativasRestantes > 0) {
                exibirBoneco(erros);
                System.out.print("Palavra: ");
                for (char c : palavraAdivinhada) {
                    System.out.print(c + " ");
                }
                System.out.println("\nTentativas restantes: " + tentativasRestantes);

                if (ehFruta) {
                    System.out.println("Dica: É uma fruta!");
                } else if (ehProfissao) {
                    System.out.println("Dica: É uma profissão!");
                }

                Boolean loop = true;
                char letra = ' ';
                while (loop) {
                    System.out.print("Digite uma letra: ");
                    String input = scanner.next().toUpperCase();
                    if (input.length() == 1 && Character.isLetter(input.charAt(0))) {
                        letra = input.charAt(0);
                        if (!letrasDigitadas[letra - 'A']) {
                            letrasDigitadas[letra - 'A'] = true;
                            loop = false;
                        } else {
                            System.out.println("Você já tentou essa letra.");
                        }
                    } else {
                        System.out.println("Por favor, digite uma letra válida!");
                    }
                }

                boolean letraEncontrada = false;
                for (int i = 0; i < tamanhoPalavra; i++) {
                    if (palavraSecreta.charAt(i) == letra) {
                        palavraAdivinhada[i] = letra;
                        letraEncontrada = true;
                    }
                }

                if (letraEncontrada) {
                    if (Arrays.equals(palavraAdivinhada, palavraSecreta.toCharArray())) {
                        System.out.println("Parabéns! Você adivinhou a palavra: " + palavraSecreta);
                        break;
                    }
                } else {
                    tentativasRestantes--;
                    erros++;
                }
            }

            System.out.println("\n=== FIM DO JOGO ===");
            exibirBoneco(erros);
            System.out.println("Palavra: " + palavraSecreta);
            System.out.print("Deseja jogar novamente? (S/N): ");
        } while (scanner.next().trim().equalsIgnoreCase("S"));

        System.out.println("Obrigado por jogar!");
        scanner.close();
    }

    private static void exibirBoneco(int erros) {
        String[] partesBoneco = {
            "_____\n|\n|\n|",
            "_____\n|  O\n| \n|",
            "_____\n|  O\n|  |\n|",
            "_____\n|  O\n| /|\n|",
            "_____\n|  O\n| /|\\ \n|",
            "_____\n|  O\n| /|\\ \n| /",
            "_____\n|  O\n| /|\\ \n| /\\"
        };

        System.out.println("=== FORCA ===");
        System.out.println(partesBoneco[erros]);
    }
}
