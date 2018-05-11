package casa;

import java.io.*;

public class Pedregal {

	public static void main(String[] args) 
	{	
		File archivo = null;
		FileWriter fichero = null;
        FileReader fr = null;
		BufferedReader br = null;
        PrintWriter pw = null; 
        
        int [][] matMD;
        int [] casa = new int [2];

        boolean sePuede = false; 
        
        int tam;
        int fils, cols;
        
        String linea;
        String [] datos;
        
        try {
        	
	        archivo = new File ("src/casa/pedregal.in");
            fichero = new FileWriter ("src/casa/pedregal.out");
                 
            pw = new PrintWriter(fichero);
	        fr = new FileReader(archivo);
	        br = new BufferedReader(fr);
	         
	        linea = br.readLine();
            datos = linea.split(" ");
            cols = Integer.parseInt(datos[0]);
            fils = Integer.parseInt(datos[1]);
            
            matMD = new int [fils][cols];
            
            linea = br.readLine();
            datos = linea.split(" ");    
            cols = Integer.parseInt(datos[0]); 
            fils = Integer.parseInt(datos[1]);
            
            casa[0] = fils;  casa[1] = cols;
            
            tam = Integer.parseInt(br.readLine());
            
            for(int i=0; i<tam; i++)
            {
            	 linea = br.readLine();
                 datos = linea.split(" ");    
                 cols = Integer.parseInt(datos[0])-1; 
                 fils = Integer.parseInt(datos[1])-1;
                 
                 matMD[fils][cols] = 1;
          }
            
//            for(int i=0; i<(matMD.length); i++)
//            {
//            	for(int j=0; j<(matMD[i].length); j++)
//            		System.out.print("(" + matMD[i][j] + ") ");
//    			System.out.println("");
//            }		
//            System.out.println("");
            
            for(int i = matMD.length-1; i>=0; i--)
    			for(int j = matMD[i].length-1; j>=0; j--)
    			{
	           		if(i<(matMD.length-1))
						if(j<(matMD[i].length-1))
							matMD[i][j] += matMD[i+1][j] + matMD[i][j+1] - matMD[i+1][j+1]; 			
						else
							matMD[i][matMD[i].length-1] += matMD[i+1][matMD[i].length-1];
					else
						if(j<(matMD[i].length-1))
							matMD[matMD.length-1][j] += matMD[matMD.length-1][j+1];
    			}
//	        
//
//            for(int i=0; i<(matMD.length); i++)
//            {
//            	for(int j=0; j<(matMD[i].length); j++)
//            		System.out.print("(" + matMD[i][j] + ") ");
//    			System.out.println("");
//            }		
//            System.out.println("");
            
            int i,j;
            i=j=0;
            char sen = 'S';
            
            while(i<=(matMD.length-casa[0]) && sePuede == false)
            {
            	j=0;
            	while(j<=(matMD[i].length-casa[1]) && sePuede == false)
    			{
    				if(i+casa[0]<matMD.length)
    				{
    					if(j+casa[1]<matMD[i].length)
    					{
    						if(matMD[i][j] - 
									matMD[i+casa[0]][j] - 
											matMD[i][j+casa[1]] + 
													matMD[i+casa[0]][j+casa[1]] == 0)
    							sePuede = true;			
    					}
    					else
    					{
    						if((matMD[i][j] - 
	    							matMD[i+casa[0]][j]) == 0)
    							sePuede = true;
    					}
    				}
    				else
    				{
    					if(j+casa[1]<matMD[i].length)
    					{
    						if((matMD[i][j] - 
    								matMD[i][j+casa[1]]) == 0)
    							sePuede = true;
    					}
    					else
    						if(matMD[i][j] == 0)
    							sePuede = true;
    				}
    				j++;
    			}
            	i++;
            }
            
            if(sePuede == false)
            	i=0;
            while(i<=(matMD.length-casa[1]) && sePuede == false)
            {
            	sen = 'O';
            	j=0;
            	while(j<=(matMD[i].length-casa[0]) && sePuede == false)
    			{
    				if(i+casa[1]<matMD.length)
    				{
    					if(j+casa[0]<matMD[i].length)
    					{
    						if(matMD[i][j] - 
									matMD[i+casa[1]][j] - 
											matMD[i][j+casa[0]] + 
													matMD[i+casa[0]][j+casa[1]] == 0)
    							sePuede = true;			
    					}
    					else
    					{
    						if((matMD[i][j] - 
	    							matMD[i+casa[1]][j]) == 0)
    							sePuede = true;
    					}
    				}
    				else
    				{
    					if(j+casa[0]<matMD[i].length)
    					{
    						if((matMD[i][j] - 
    								matMD[i][j+casa[0]]) == 0)
    							sePuede = true;
    					}
    					else
    						if(matMD[i][j] == 0)
    							sePuede = true;
    				}
    				j++;
    			}
            	i++;
            }
            
            if(sePuede == true)
            {
                pw.println("SI");
             	pw.println(""+i+" "+j);
             	pw.println(sen);
            }
            else
            	pw.println("NO");
            
 	    } catch(Exception e){
		 e.printStackTrace();
            } finally {
            try {
            // Nuevamente aprovechamos el finally para 
            // asegurarnos que se cierra el fichero.
            if (null != fichero)
               br.close();

            if (null != archivo)
               pw.close();

            } catch (Exception e2) {
               e2.printStackTrace();
            }
            }
	}
}
