class Quote {
    private int id;
    private String content;
    private String writer;

    public Quote(String content, String writer) {
        this.content = content;
        this.writer = writer;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getWriter() {
        return writer;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void update(String content, String writer) {
        this.content = content;
        this.writer = writer;
    }
}