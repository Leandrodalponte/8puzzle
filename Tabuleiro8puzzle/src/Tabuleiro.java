import java.util.*;

public class Tabuleiro {


        public static void main(String[] args) {


                
                
                int[] matriz = {0,2,3,
                                1,8,5,
                                4,7,6,};
                int hueristic = 2;
                EightPuzzle iniciar = new EightPuzzle(matriz, hueristic, 0);
                int[] ultimo = { 0,1,2,
                                 3,4,5,
                                 6,7,8,};
                EightPuzzle fim = new EightPuzzle(ultimo, hueristic, 0);

                astar(iniciar, fim);



        }

        public static void astar(EightPuzzle iniciar, EightPuzzle fim)
        {
                if(iniciar.inverter() % 2 == 1)
                {
                        System.out.println("NÃO FUNFA");
                        return;
                }

                LinkedList<EightPuzzle> fechados = new LinkedList<>();
                
                PriorityQueue<EightPuzzle> abertos = new PriorityQueue<>();

                abertos.add(iniciar);


                while(abertos.size() > 0){

                        EightPuzzle x = abertos.peek();


                        if(x.mapIgual(fim))
                        {

                                 Stack<EightPuzzle> toDisplay = reconstruct(x);
                                 System.out.println("Mostrar tabuleiro inicial ");
                                 System.out.println(iniciar.toString());
                                 print(toDisplay);
                                 return;

                        }

                        fechados.add(abertos.poll());
                        LinkedList <EightPuzzle> neighbor = x.getChildren();
             
                        while(neighbor.size() > 0)
                        {
                                EightPuzzle y = neighbor.removeFirst();

                                if(fechados.contains(y)){

                                        continue;
                                }


                                if(!fechados.contains(y)){

                                        abertos.add(y);
                     
                                }
                
                        }
              
                }
        }

        public static void print(Stack<EightPuzzle> x)
        {
            int teste = 0;
                while(!x.isEmpty())
                {
                        EightPuzzle temp = x.pop();
                        ++teste;
                        System.out.println("Movimento " + teste);
                        System.out.println(temp.toString());
                }
        }

        public static Stack<EightPuzzle> reconstruct(EightPuzzle MetaFinal)
        {
                Stack<EightPuzzle> correctOutput = new Stack<>();

                while(MetaFinal.getParent() != null)
                {
                correctOutput.add(MetaFinal);
                MetaFinal = MetaFinal.getParent();
                }

                return correctOutput;
        }

        }