package Model;

public abstract class LibraryObject {
    public String Name;
    public int ID;
    public String Category; 
    public int Status;      

    public String getName() { return Name; }
    public void setName(String name) { this.Name = name; }

    public int getID() { return ID; }
    public void setID(int id) { this.ID = id; }

    public String getCategory() { return Category; }
    public void setCategory(String category) { this.Category = category; }

    public int getStatus() { return Status; }
    public void setStatus(int status) { this.Status = status; }

   
    public abstract void printInfo();
}