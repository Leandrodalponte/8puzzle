import java.util.*;

    public final class EightPuzzle implements Comparable <Object> {


            int[] puzzle = new int[9];
            int a= 0;
            int hueristic_type = 0;
            int b = 0;
            int c = 0;
            EightPuzzle parent = null;

    /**
     *
     * @param p
     * @param h_type
     * @param cost
     */
    public EightPuzzle(int[] p, int h_type, int cost)
            {
                    this.puzzle = p;
                    this.hueristic_type = h_type;
                    this.a = (h_type == 1) ?  h1(p) : h2(p);
                    this.b = cost;
                    this.c = a + b;
            }
            public int getF_n()
            {
                    return c;
            }
            public void setParent(EightPuzzle input)
            {
                    this.parent = input;
            }
            public EightPuzzle getParent()
            {
                    return this.parent;
            }

            public int inverter()
            {
                   
                    // * Descrição: Para qualquer outra configuração além da meta,
                     //* Sempre que um filho com um número maior sobre ele precede um
                     //* filho com um menor número, as duas peças são invertidas
                      
                    int inverter = 0;
                    for(int i = 0; i < this.puzzle.length; i++ )
                    {
                            for(int j = 0; j < i; j++)
                            {
                                    if(this.puzzle[i] != 0 && this.puzzle[j] != 0)
                                    {
                                    if(this.puzzle[i] < this.puzzle[j])
                                            inverter++;
                                    }
                            }

                    }
                    return inverter;

            }

    /**
     *
     * @param listar
     * @return
     */
    public int h1(int[] listar)
            // h1 = o número de quadrdos fora do lugar
            {
                    int tot = 0;
                    for(int i = 0; i < listar.length; i++)
                    {
                            if(listar[i] != i && listar[i] != 0)
                                    tot++;
                    }
                    return tot;
            }
            public LinkedList<EightPuzzle> getChildren()
            {
                    LinkedList<EightPuzzle> filhos = new LinkedList<EightPuzzle>();
                    int loc = 0;
            int array[] = new int[this.puzzle.length];
            EightPuzzle pDireita, pCima, pBaixo, pEsquerda;
                    while(this.puzzle[loc] != 0)
                    {
                            loc++;
                    }
                    if(loc % 3 == 0){
                            array = this.puzzle.clone();
                            array[loc] = array[loc + 1];
                            array[loc + 1] = 0;
                            pDireita = new EightPuzzle(array, this.hueristic_type, this.b + 1);
                            pDireita.setParent(this);
                            filhos.add(pDireita);

                    }else if(loc % 3 == 1){
                    //Manda para direita
                            array = this.puzzle.clone();
                            array[loc] = array[loc + 1];
                            array[loc + 1] = 0;

                            pDireita = new EightPuzzle(array, this.hueristic_type, this.b + 1);
                            pDireita.setParent(this);
                            filhos.add(pDireita);
                            //Manda para esquerda
                            array = this.puzzle.clone();
                            array[loc] = array[loc - 1];
                            array[loc - 1] = 0;

                            pEsquerda = new EightPuzzle(array, this.hueristic_type, this.b + 1);
                            pEsquerda.setParent(this);
                            filhos.add(pEsquerda);
                    }else if(loc % 3 == 2){
                    // Manda para esquerda
                            array = this.puzzle.clone();
                            array[loc] = array[loc - 1];
                            array[loc - 1] = 0;

                            pEsquerda = new EightPuzzle(array, this.hueristic_type, this.b + 1);
                            pEsquerda.setParent(this);
                            filhos.add(pEsquerda);
                    }              

                    if(loc / 3 == 0){
                    //manda para baixo
                            array = this.puzzle.clone();
                            array[loc] = array[loc + 3];
                            array[loc + 3] = 0;

                            pBaixo = new EightPuzzle(array, this.hueristic_type, this.b + 1);

                            pBaixo.setParent(this);

                            filhos.add(pBaixo);


                    }else if(loc / 3 == 1 ){
                            //Manda para cima
                            array = this.puzzle.clone();
                            array[loc] = array[loc - 3];
                            array[loc - 3] = 0;

                            pCima = new EightPuzzle(array, this.hueristic_type, this.b + 1);
                            pCima.setParent(this);

                            filhos.add(pCima);
                            //Manda para cima
                            array = this.puzzle.clone();
                            array[loc] = array[loc + 3];
                            array[loc + 3] = 0;

                            pBaixo = new EightPuzzle(array, this.hueristic_type, this.b + 1);
                            pBaixo.setParent(this);

                            filhos.add(pBaixo);
                    }else if (loc / 3 == 2 ){
                            //Manda para cima
                            array = this.puzzle.clone();
                            array[loc] = array[loc - 3];
                            array[loc - 3] = 0;

                            pCima = new EightPuzzle(array, this.hueristic_type, this.b + 1);
                            pCima.setParent(this);

                            filhos.add(pCima);
                    }

                    return filhos;
            }
            public int h2(int[] listar)
               // H2 = a soma das distâncias dos quadrados e das suas posições sobre a meta
               // para cada item encontrar sua posição 
               // e calcula quantas posições ele precisa se mover para chegar na posição
            {
                    int tot = 0;
                    int linha = 0;
                    int coluna = 0;
                    for(int i = 0; i < listar.length; i++)
                    {
                            if(listar[i] != 0)
                            {
                                    linha = listar[i] / 3;
                                    coluna = listar[i] % 3;
                                    linha = Math.abs(linha - (i / 3));
                                    coluna = Math.abs(coluna - (i % 3));
                                    tot += linha;
                                    tot += coluna;
                            }

                    }
                    return tot;
            }

            @Override
            public String toString()
            {
                    String x = "";
                    for(int i = 0; i < this.puzzle.length; i++){
                            x += puzzle[i] + " ";
                            if((i + 1) % 3 == 0)
                                    x += "\n";
                    }
                    return x;
            }
            @Override
            public int compareTo(Object input) {


                    if (this.c < ((EightPuzzle) input).getF_n())
                            return -1;
                    else if (this.c > ((EightPuzzle) input).getF_n())
                            return 1;
                    return 0;
            }

            public boolean igual(EightPuzzle test){
                    if(this.c != test.getF_n())
                            return false;
                    for(int i = 0 ; i < this.puzzle.length; i++)
                    {
                            if(this.puzzle[i] != test.puzzle[i])
                                    return false;
                    }
                    return true;
            }
            public boolean mapIgual(EightPuzzle teste){
                    for(int i = 0 ; i < this.puzzle.length; i++)
                    {
                            if(this.puzzle[i] != teste.puzzle[i])
                                    return false;
                    }
                    return true;
            }

    }