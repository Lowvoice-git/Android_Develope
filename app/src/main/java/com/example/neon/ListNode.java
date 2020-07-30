package com.example.neon;

public class ListNode {
    private int icon;
    private String AquariumName;
    private String AquariumCount;

    public ListNode(int icon,String AquariumName,String AquariumCount){
        this.icon = icon;
        this.AquariumName = AquariumName;
        this.AquariumCount = AquariumCount;
    }

    public int getIcon() {
        return this.icon;
    }

    public String getAquariumName() {
        return this.AquariumName;
    }

    public String getAquariumCount() {
        return this.AquariumCount;
    }
}
