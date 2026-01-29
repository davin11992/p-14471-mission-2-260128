import java.util.ArrayList;

class QuoteRepository {
    private int id = 0;
    private final ArrayList<Quote> quotes = new ArrayList<>();

    public int save(String content, String writer) {
        id++;
        Quote quote = new Quote(id, content, writer);
        quotes.add(quote);
        return id;
    }

    public Quote findById(int id) {
        for (Quote quote : quotes) {
            if (quote.getId() == id) {
                return quote;
            }
        }
        return null;
    }

    public ArrayList<Quote> findAll() {
        return quotes;
    }

    public boolean deleteById(int id) {
        Quote quote = findById(id);
        if (quote == null) {
            return false;
        }
        quotes.remove(quote);
        return true;
    }
}