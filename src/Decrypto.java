import java.util.*; //to use scanner

//Program to decrypt

public class Decrypto {
    //some constants
    static final int deltaOne = 0x11111111;
    static final int deltaTwo = 0x22222222;

    public static void main (String[] args){
        //Initializations
        Scanner sc=new Scanner(System.in);

        //variable initializations
        int K[] = {0x00000000,0x00000000,0x00000000,0x00000000};
        int L=0x00000000;
        int R=0x00000000;
        int Lfinal=0x00000000;
        int Rfinal=0x00000000;

        System.out.println("Program start...");
        System.out.println("Maximum value supported is "+Integer.toHexString(Integer.MAX_VALUE));

        //FILL KEY ARRAY BLOCK--------------------------
        for (int i=0;i<4;i++) {
            boolean goOn = false;
            while (goOn == false) {
                try {
                    System.out.print("Provide decryption key K[" + i + "] in hex string (without 0x)");
                    String str = "0x" + sc.nextLine();
                    K[i] = Integer.decode(str);
                    goOn = true;
                } catch (Exception ex) {
                    System.out.println("Error! Try again...");
                }
            }
        }
        //END FILL KEY ARRAY BLOCK------------------------------

        //loop to test the array
        for (int i=0;i<4;i++){
            System.out.println("You have entered: "+Integer.toHexString(K[i])+" = "+K[i]);
        }

        //INPUT L[0] AND R[0] BLOCK-------------------------------
        boolean goOn = false;
        while (goOn==false)
        {
            try{
                //L[0]
                System.out.print("Provide ciphertext L[2] in hex string (without 0x)");
                String str="0x"+sc.nextLine();
                L=Integer.decode(str);

                //R[0]
                System.out.print("Provide ciphertext R[2] in hex string (without 0x)");
                str="0x"+sc.nextLine();
                R=Integer.decode(str);
                goOn=true;
            }
            catch(Exception ex)
            {
                System.out.println("Error! Try again...");
            }
        }
        //END INPUT L[2] AND R[2] BLOCK-------------------------------

        //Show inputs
        System.out.println("You have entered L[2]: "+Integer.toHexString(L)+" = "+L);
        System.out.println("You have entered R[2]: "+Integer.toHexString(R)+" = "+R);
        System.out.println("Begin decryption...");

        //DECRYPTION BLOCK---------------------------------------------
        Rfinal = R - (((L << 4) + K[2]) ^ (L + deltaTwo) ^ ((L >> 5) + K[3]));
        Lfinal = L - (((Rfinal << 4)+K[0])^(Rfinal+deltaOne)^((Rfinal >> 5)+ K[1]));
        //END DECRYPTION BLOCK---------------------------------------------

        System.out.println("L[0] = "+Lfinal+" = "+Integer.toHexString(Lfinal));
        System.out.println("R[0] = "+Rfinal+" = "+Integer.toHexString(Rfinal));
    }
}
