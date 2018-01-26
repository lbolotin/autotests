package Lesson3;

public class Tester {
    private String fio;
    private Integer age;
    private Integer tel;
    private NoteBook notebook;

    public void setFio(String myfio){
        this.fio = myfio;
    }
    public String getFio() {
        return fio;
    }

    public void sayName() {
        String testername = this.fio;
        System.out.println(testername);
    }

    public void setNotebook(NoteBook notebook) {
        this.notebook = notebook;
    }
    public NoteBook getNotebook() {
        return notebook;
    }

    public void showTestersNotebook(){
        System.out.println(this.fio+" plays on "+notebook.getModel());
    }

    public Tester(String fio, NoteBook notebook) {
        this.fio = fio;
        this.notebook = notebook;
    }

    public Tester() {
    }
}
