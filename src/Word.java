public class Word {

    String word1;
    String word2;
    String word3;
    String word4;
    String word5;
    String word6;

    public Word(String word1, String word2, String word3, String word4, String word5, String word6){

        this.word1=word1;
        this.word2=word2;
        this.word3=word3;
        this.word4=word4;
        this.word5=word5;
        this.word6=word6;
    }
}
    abstract class Sentence extends Word {

    String sentence1=word1+" "+word2+" "+word3;
    String sentence2=word4+" "+word5+" "+word6;

        public Sentence(String word1, String word2, String word3, String word4, String word5, String word6) {
            super(word1, word2, word3, word4, word5, word6);
        }

        public abstract String toString(String word0);
    }

    class Text extends Sentence{

        public void setText(String addition) {
            Text =Text+" "+addition;
        }

        String Text=sentence1+" "+sentence2;

        public Text(String word1, String word2, String word3, String word4, String word5, String word6) {
            super(word1, word2, word3, word4, word5, word6);
        }

        @Override
        public String toString(String word0) {
            return "Заголовок "+word0+" "+
                    Text;
        }
    }
