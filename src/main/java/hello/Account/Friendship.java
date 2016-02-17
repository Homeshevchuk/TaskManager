package hello.Account;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Bogdan on 2/16/2016.
 */
@Entity
public class Friendship {
    @Id
    private long id;
    @Column
    private long firstFriendId;
    @Column
    private long secondFriendId;
    @Column
    private boolean approved;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getFirstFriendId() {
        return firstFriendId;
    }

    public void setFirstFriendId(long firstFriendId) {
        this.firstFriendId = firstFriendId;
    }

    public long getSecondFriendId() {
        return secondFriendId;
    }

    public void setSecondFriendId(long secondFriendId) {
        this.secondFriendId = secondFriendId;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Friendship)) return false;

        Friendship that = (Friendship) o;

        if (getId() != that.getId()) return false;
        if (getFirstFriendId() != that.getFirstFriendId()) return false;
        if (getSecondFriendId() != that.getSecondFriendId()) return false;
        return isApproved() == that.isApproved();

    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + (int) (getFirstFriendId() ^ (getFirstFriendId() >>> 32));
        result = 31 * result + (int) (getSecondFriendId() ^ (getSecondFriendId() >>> 32));
        result = 31 * result + (isApproved() ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Friendship{" +
                "id=" + id +
                ", firstFriendId=" + firstFriendId +
                ", secondFriendId=" + secondFriendId +
                ", approved=" + approved +
                '}';
    }
}
