import java.util.*;
import java.io.*;

public class Hoof {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        
        char[][] outcome = new char[N][N];

        
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j <= i; j++) {
                outcome[i][j] = line.charAt(j);
                if (line.charAt(j) == 'W') {
                    outcome[j][i] = 'L';
                } else if (line.charAt(j) == 'L') {
                    outcome[j][i] = 'W';
                } else {
                    outcome[j][i] = 'D';
                }
            }
        }

        // Process each game query
        for (int l = 0; l < M; l++) {
            st = new StringTokenizer(br.readLine());
            int s1 = Integer.parseInt(st.nextToken()) - 1; 
            int s2 = Integer.parseInt(st.nextToken()) - 1; 

            int count = 0;

            
            for (int L = 0; L < N; L++) {
                for (int R = 0; R < N; R++) {
                    if (outcome[L][s1] == 'W' && outcome[R][s2] == 'W') {
                        count++;
                    }
                }
            }

            
            System.out.println(count);
        }
    }
}

