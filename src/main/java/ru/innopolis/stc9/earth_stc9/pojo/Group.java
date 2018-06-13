package ru.innopolis.stc9.earth_stc9.pojo;

/**
 * @author Aleksandr Tikhonov - created 29.05.2018
 */

public class Group {
    private int groupId;
    private String groupName;
    private String groupDesc;

    public Group(int groupId, String groupName, String groupDesc) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.groupDesc = groupDesc;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupDesc() {
        return groupDesc;
    }

    public void setGroupDesc(String groupDesc) {
        this.groupDesc = groupDesc;
    }
}
