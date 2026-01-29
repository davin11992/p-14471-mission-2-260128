class Quote {
    private final int id;
    private String content;
    private String writer;

    public Quote(int id, String content, String writer) {
        this.id = id;
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

    public void update(String content, String writer) {
        this.content = content;
        this.writer = writer;
    }
}