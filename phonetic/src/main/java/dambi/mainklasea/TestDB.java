package dambi.mainklasea;

import dambi.atzipena.*;

public class TestDB {
    
        public static void main(String[] args) {
            SQLKudeatu sql = new SQLKudeatu();
            sql.connect();
            sql.terminoakImprimatu();
        }
}
