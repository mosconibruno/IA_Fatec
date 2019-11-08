
package manipula_imagens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.awt.image.LookupTable;
import java.io.*;
import java.awt.image.WritableRaster;
import javax.imageio.ImageIO;

public class Manipula_Imagens {
    
    public static void main(String[] args) {
        JFrame frame = new imagens();
        frame.show();
    }   
}

class imagens extends JFrame implements ActionListener{
    double pixel;
    
    public imagens(){ //Metodo construtor
        
        setTitle("processamento de imagens");
        setSize(300, 400);
        addWindowListener(new WindowAdapter()
            { public void windowClosing(WindowEvent e)
                { System.exit(0);
                }
            });
        
        Container container = getContentPane();
        painel = new objetos_graficos ();
        container.add(painel, "Center");
        
        JMenu fileMenu = new JMenu("arquivo");
        abrirItem = new JMenuItem("abrir");
        abrirItem.addActionListener(this);
        fileMenu.add(abrirItem);
        
        sairItem = new JMenuItem("sair");
        sairItem.addActionListener(this);
        fileMenu.add(sairItem);
        
        JMenu editMenu = new JMenu("editar");
        item_opaco = new JMenuItem("opaco");
        item_opaco.addActionListener(this);
        editMenu.add(item_opaco);
        
        intensificarItem = new JMenuItem("intensificar");
        intensificarItem.addActionListener(this);
        editMenu.add(intensificarItem);
        
        iluminarItem = new JMenuItem("iluminar");
        iluminarItem.addActionListener(this);
        editMenu.add(iluminarItem);
        
        detectarborda = new JMenuItem("detectar_borda");
        detectarborda.addActionListener(this);
        editMenu.add(detectarborda);
        
        invertercor = new JMenuItem("inverter_cor");
        invertercor.addActionListener(this);
        editMenu.add(invertercor);
        
        rotacionar = new JMenuItem("rotacionar");
        rotacionar.addActionListener(this);
        editMenu.add(rotacionar);
        
        gravargb = new JMenuItem("gravar rgb");
        gravargb.addActionListener(this);
        editMenu.add(gravargb);
        
        apagargb = new JMenuItem("apagar rgb");
        apagargb.addActionListener(this);
        editMenu.add(apagargb);
        
        imagem_manipulada = new JMenuItem("grava imagem");
        imagem_manipulada.addActionListener(this);
        editMenu.add(imagem_manipulada);
        
        cores = new JMenuItem("mostra rgb");
        cores.addActionListener(this);
        editMenu.add(cores);
        
        rede_neural_1 = new JMenuItem("treina rede neural");
        rede_neural_1.addActionListener(this);
        editMenu.add(rede_neural_1);
        
        rede_neural_2 = new JMenuItem("testa rede neural");
        rede_neural_2.addActionListener(this);
        editMenu.add(rede_neural_2);
        
        rede_neural_3 = new JMenuItem("imagem rede neural");
        rede_neural_3.addActionListener(this);
        editMenu.add(rede_neural_3);
        
        rede_neural_4 = new JMenuItem("testa generalização");
        rede_neural_4.addActionListener(this);
        editMenu.add(rede_neural_4);
        
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        setJMenuBar(menuBar);
    }//fim do metodo construtor imagens
    
    int xPos, yPos, u, i;
    
    public void paint(Graphics g){
        super.paint(g);
        
        g.setColor(Color.red);
        g.drawString("x= " + xPos + " y= " + yPos, xPos, yPos);
        g.setColor(Color.orange);
        g.fillRect(u, i, 8, 8);
        repaint();
    }
    
    public void actionPerformed(ActionEvent evt){
        Object source = evt.getSource();
        if (source == abrirItem){
            JFileChooser chooser = new JFileChooser();
            chooser.setCurrentDirectory(new File("."));
            
            chooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
                public boolean accept(File f) {
                   String nome = f.getName().toLowerCase();
                   return nome.endsWith(".gif")
                           || nome.endsWith(".jpg")
                           || nome.endsWith(".jpeg")
                           || f.isDirectory();
                }
                public String getDescription() {                  
                    return "imagem do arquivo";
                }
            });
            
            int r = chooser.showOpenDialog(this);
            if(r == JFileChooser.APPROVE_OPTION){
                String name = chooser.getSelectedFile().getAbsolutePath();
                painel.loadImage(name);
            }
        }
        else if (source == sairItem) System.exit(0);
        else if (source == item_opaco) painel.blur();
        else if (source == intensificarItem) painel.intensifica();
        else if (source == iluminarItem) painel.ilumina();
        else if (source == detectarborda) painel.borda();
        else if (source == invertercor) painel.inverte();
        else if (source == rotacionar) painel.rotaciona();
        else if (source == cores) painel.converte();
        else if (source == gravargb) painel.grava_arquivo();
        else if (source == apagargb) painel.apaga_arquivo();
        else if (source == imagem_manipulada) painel.encaminha();
        else if (source == rede_neural_1) painel.treina_rede_neural();
        else if (source == rede_neural_2) painel.teste_rede_neural();
        else if (source == rede_neural_3) painel.gera_imagem_rede_neural();
        else if (source == rede_neural_4) painel.teste_generalizacao();
    }
    
    private objetos_graficos painel;
    private JMenuItem abrirItem;
    private JMenuItem sairItem;
    private JMenuItem item_opaco;
    private JMenuItem intensificarItem;
    private JMenuItem iluminarItem;
    private JMenuItem detectarborda;
    private JMenuItem invertercor;
    private JMenuItem rotacionar;
    private JMenuItem imagem_manipulada;
    private JMenuItem gravargb;
    private JMenuItem apagargb;
    private JMenuItem cores;
    private JMenuItem rede_neural_1;
    private JMenuItem rede_neural_2;
    private JMenuItem rede_neural_3;
    private JMenuItem rede_neural_4;   
} // fim classe imagens

class objetos_graficos extends JPanel{
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if (imagem != null)
            g.drawImage(imagem, 0, 0, null);
    }
    
    public void loadImage(String nome){
        Image carregar_imagem = Toolkit.getDefaultToolkit().getImage(nome);
        MediaTracker tracker = new MediaTracker(this);
        tracker.addImage(carregar_imagem, 0);
        try{
            tracker.waitForID(0);
        }catch(InterruptedException e){}
        imagem = new BufferedImage(carregar_imagem.getWidth(null),
            carregar_imagem.getHeight(null), BufferedImage.TYPE_INT_BGR);
        Graphics2D g2 = imagem.createGraphics();
        g2.drawImage(carregar_imagem, 0, 0, null);
        
        WritableRaster varredura = imagem.getRaster();
        varredura.getPixels(0, 0, 20, 25, amostragem); //coordenadas iniciais e finais do tamanho da figura
        
        int p = 0; int q = 0; int s = 0; int []y = {0};
        
        for (int u = 0; u <= amostragem.length - 1; u = u + 3){
            red[p] = amostragem[u]; p = p+1;
        }
        
        for (int u = 0; u <= amostragem.length - 1; u = u + 3){
            green[p] = amostragem[u]; q = q+1;
        }
        
        for (int u = 2; u <= amostragem.length -1; u = u + 3){
            blue[s] = amostragem[u]; s = s+1;
        }
        
        int jb = 0;
        for (linha = 0; linha <= 25; linha ++)
                for (coluna = 0; coluna <=20; coluna++){
                    //buffer.append(" rgb[" + linha +"][" + coluna + "] " + red[jb] + " " + green[jb] + "
                     if(((red[coluna] == 0)&&(green[coluna]==0))&&(blue[coluna] == 0) ){
                     // y[coluna]= linha;
                        //for (int ll = 0; ll <= 299; ll++)
                            //System.out.println(y[11]);
                    }
         jb= jb + 1;}
      repaint();
    }
    
    private void filtro(BufferedImageOp op){
        BufferedImage imagemfiltrada
                = new BufferedImage(imagem.getWidth(), imagem.getHeight(),
                imagem.getType());
        op.filter(imagem, imagemfiltrada);
        imagem = imagemfiltrada;
        repaint();
    }
    
    public void encaminha(){
        gravaimagem(imagem);
    }
    
    private void convolucao(float[] elementos){
        Kernel kernel = new Kernel(3, 3, elementos);
        ConvolveOp op = new ConvolveOp(kernel);
        filtro(op);
    }
    
    public void blur(){
        float weight = 1.0f/9.0f;
        float[] elementos = new float[9];
        for (int i = 0; i < 9; i++)
            elementos[i] = weight;
        convolucao(elementos);
    }
    
    public void intensifica(){
        float[] elementos = {
          0.0f, -1.0f, 0.0f,
            -1.0f, 5.f, -1.0f,
            0.0f, -1.0f, 0.0f
        };
        convolucao(elementos);
    }
    
    void borda(){
        float[] elementos = 
        { 0.0f, -1.0f, 0.0f,
            -1.0f, 4.f, -1.0f,
            0.0f, -1.0f, 0.0f
        };
        convolucao(elementos);
    }
    
    public void ilumina(){
        float a = 1.5f;
        float b = -20.0f;
        RescaleOp op = new RescaleOp(a, b, null);
        filtro(op);
    }
    
    void inverte(){
        byte negativo[] = new byte[256];
        for (int i = 0; i < 256; i++)
            negativo[i] = (byte)(255 - i);
        ByteLookupTable tabela = new ByteLookupTable(0, negativo);
        LookupOp op = new LookupOp(tabela, null);
        filtro(op);
    }
    
    void rotaciona(){
       AffineTransform transforma = 
            AffineTransform.getRotateInstance(Math.toRadians(0), 
               imagem.getWidth() / 2, imagem.getHeight() / 2);
       AffineTransformOp op = new AffineTransformOp(transforma, AffineTransformOp.TYPE_BILINEAR);
       filtro(op);
    }
    
    void converte(){
        int largura = imagem.getWidth();
        int altura = imagem.getHeight();
        
        WritableRaster raster = imagem.getRaster();
        ColorModel model = imagem.getColorModel();
        int[] amostragem = new int [4];
        
        int[] k = new int [largura*altura];
        int i = 0; int j = 0;
        Object data = raster.getDataElements(0, 0, amostragem);
        
        int [][][] p = new int[altura][largura][4];
        
        for(i = 0; i<altura; i++)
            for(j = 0; j < largura ; j++){
                p[i][j] = raster.getPixel(j, i, amostragem);
                System.out.println("["+ i + "]" + "\t " +"["+ j+ "]" + "\t" + p[i][j][0]+"\t"+p[i][j][1]+"\t"+p[i][j][2]+"\t" + p[i][j][3]);
            }
    }
    
    String p1;
    int x, y, yPos, xCor, yCor;
    BufferedImage g;
    
    public void gravaimagem(BufferedImage image){
        try{
            ImageIO.write(image, "JPG", new File("memoria.jpg"));
        }catch(Exception erro){
            System.out.println("Erro na gravacao");
        }
    }
    
    public void apaga_arquivo(){
        try{
            StringBuffer memoria = new StringBuffer();
            BufferedReader arqentrada;
            arqentrada = new BufferedReader (new FileReader ("rgb.txt"));
            String linha = "";
            while((linha = arqentrada.readLine()) != null){
                memoria.append(linha);
            }
            if( memoria.length() != 0){
                
                memoria.delete(0, 1000000000);
                
                BufferedWriter saida1 = new BufferedWriter(new FileWriter("rgb.txt"));
                saida1.write(memoria.toString());
                saida1.flush();
                saida1.close();
            
            }else{
                System.out.println("Arquivo vazio");
            }
            
        }catch (Exception erro){
            System.out.println("Erro no delete do arquivo");
        }
    }
    
    public void grava_arquivo(){
        try{
            
            BufferedWriter saida = new BufferedWriter(new FileWriter ("rgb.txt", true));
            saida.write("\n" + buffer );
            saida.flush();
            saida.close();
            
        }catch(Exception erro){
            System.out.println("Erro na gravacao!");
        }
    }
    
    public void treina_rede_neural(){
        int largura = imagem.getWidth();
        int altura = imagem.getHeight();
        
        WritableRaster raster = imagem.getRaster();
        ColorModel model = imagem.getColorModel();
        int[] amostragem = new int [3];
        
        int[] k = new int [largura*altura];
        int i = 0; int j = 0; int m = 0;
        Object data = raster.getDataElements(0, 0, amostragem);
        
        int [] h = new int [4];
        int [][][] p = new int[altura][largura][3];
        
        System.out.println("//red");
        System.out.println("double [] r= { ");
        
        for(i = 0; i<altura ; i++)
            for( j = 0; j < largura; j++ ){
                p[i][j] = raster.getPixel(j, i, amostragem);
                System.out.println(p(double)[i][j][0]/255+",");
                rr[m] = (double)p[i][j][0]/255;
                m = m+1;
            }
        m = 0;
        System.out.println("};");
        System.out.println("\n\n");
        System.out.println("//green");
        System.out.println("double [] g= {");
        
        for(i = 0;i < altura ; i++)
            for( j = 0; j < largura ; j++){
                p[i][j] = raster.getPixel(j, i, amostragem);
                
                System.out.println((double)p[i][j][1]/255+",");
                gg[m] = (double)p[i][j][1]/255;
                m =  m+1;
            }
        
        m = 0;
        System.out.println("};");
        System.out.println("\n\n");
        System.out.println("//blue");
        System.out.println("double [] b= { ");
        for(i = 0; i<altura; i++)
            for( j = 0; j < largura ; j++){
                p[i][j] = raster.getPixel(j, i, amostragem);
                System.out.println((double)p[i][j][2]/255+",");
                bb[m] = (double)p[i][j][2]/255;
                m = m=1;
                System.out.println("};");
                bn.iteracao_IV(rr, gg, bb);
            }
        
        }
    
        public void teste_rede_neural() {
            bn.testa_backpropagation_ncamandas(bn.h1, bn.h2, bn.h3, 
                    bn.v0, bn.v1, bn.v2, bn.v3, bn.v4, bn.v5, bn.v6, 
                        bn.w0, bn.w1, bn.w2, bn.w3, bn.w4, bn.w5, bn.w6,
                            bn.y0, x);
        }
        
        public void gera_imagem_rede_neural(){
            
            int largura = 20;
            int altura = 25;
            double [] arranjo_cores = new double [3*altura*largura];
            
            imagem_neural = new BufferedImage(20, 25, BufferedImage.TYPE_INT_RGB);
            WritableRaster raster = imagem_neural.getRaster();
            
            int tt = 0;
            for(int t = 0; t < arranjo_cores.length; t=t+3){
                arranjo_cores[t] = bn.y1f[tt]*255;
                arranjo_cores[t+1] = bn.y2f[tt]*255;
                arranjo_cores[t+2] = bn.y3f[tt]*255;
                
                System.out.println("arranjo cores [0]" + arranjo_cores[t]);
                System.out.println("arranjo cores [1]" + arranjo_cores[t+1]);
                System.out.println("arranjo cores [2]" + arranjo_cores[t+2]);
                tt++;
            } 
        raster.setPixels(0, 0, largura, altura, arranjo_cores);  
        gravaimagem(imagem_neural);    
        }
        
        public void teste_generalizacao(){
            int largura = imagem.getWidth();
            int altura = imagem.getHeight();
            
            WritableRaster raster = imagem.getRaster();
            ColorModel model = imagem.getColorModel();
            int [] amostra = new int [3];
            
            int i = 0; int j = 0; int m = 0;
            Object data = raster.getDataElements(0, 0, amostra);
            
            int [][][] p = new int[altura][largura][3];
            
            for(i=0;i<altura;i++)
                for(j=0; j<largura; j++){
                    p[i][j] = raster.getPixel(j, i, amostra);
                    rr[m] = (double)p[i][j][0]/255;
                    m = m+1;
                }
            m=0;
            
            for(i = 0;i<altura; i++)
                for(j=0; j < altura; j++){
                    p[i][j] = raster.getPixel(j, i, amostra);
                    gg[m] = (double)p[i][j][1]/255;
                    m = m+1;
                }
            m=0;
            for(i=0; i<altura; i++)
                for(j=0; j<largura; j++){
                    p[i][j] = raster.getPixel(j, i, amostra);
                    bb[m] = (double)p[i][j][2]/255;
                    m = m+1;
                }
            bn.generalizacao(bn.h1, bn.h2, bn.h3, bn.v0, bn.v1, bn.v2, bn.v3, bn.v4, bn.v5,
                    bn.v6, bn.w0, bn.w1, bn.w2, bn.w3, bn.w4, bn.w5, bn.w6, bn.y0, rr, gg, bb);
        }
    
    StringBuffer buffer = new StringBuffer();
    int [] red = new int [1000000];
    int [] green = new int [1000000];
    int [] blue = new int [1000000];
    
    int linha = 0, coluna = 0;
    int[] amostragem = new int [1000000];
    int[] amostra = new int[3];
    
    private BufferedImage imagem, imagem_neural;
    
    backpropagation_ncamadas bn = new backpropagation_ncamadas();
    double []rr = new double[500]; double []gg = new double[500]; double[]bb = new double[500];
}
