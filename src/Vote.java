/**
 *
 * @author Suporte04
 */
public class Vote {
    public Vote(int number, String name){
        this.number = number;
        this.politician = name;
    }
    
    private int number;
    private String politician;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getPolitician() {
        return politician;
    }

    public void setPolitician(String politician) {
        this.politician = politician;
    }

    @Override
    public String toString() {
        return "Vote{" + "number=" + number + ", politician=" + politician + '}';
    }
    
    
    
    
}
