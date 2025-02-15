package gr.hua.dit.ds.ds_lab_2024.entities;


import jakarta.persistence.*;

@Entity
@Table
public class project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String title;


    @Column
    private String description;


    @Column
    private int goalAmount;

    @Column(nullable = false, columnDefinition = "int default 0")
    private int currentAmount;

    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean isVisible;


    public project() {
    }

    public project(int id, String title, String description, int goalAmount, int currentAmount, boolean isVisible) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.goalAmount = goalAmount;
        this.currentAmount = currentAmount;
        this.isVisible = isVisible;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getGoalAmount() {
        return goalAmount;
    }

    public void setGoalAmount(int goalAmount) {
        this.goalAmount = goalAmount;
    }

    public int getCurrentAmount() {
        return currentAmount;
    }

    public void setCurrentAmount(int currentAmount) {
        this.currentAmount = currentAmount;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }
}
