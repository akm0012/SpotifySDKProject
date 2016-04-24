package com.mobiquity.amarshall.spotifysync.Models;

import java.util.Iterator;
import java.util.List;

/**
 * The track that is stored in the server.
 */
public class SpoqTrack {
    private String trackId;
    private boolean skipped;
    /** Used for sorting. */
    private long timeAdded;

    /** The list of the users that down-voted this track. */
    private List<SpoqUser> usersThatVoted;

    public String getTrackId() {
        return trackId;
    }

    public void setTrackId(String trackId) {
        this.trackId = trackId;
    }

    public boolean isSkipped() {
        return skipped;
    }

    public void setSkipped(boolean skipped) {
        this.skipped = skipped;
    }

    public List<SpoqUser> getUsersThatVoted() {
        return usersThatVoted;
    }

    public void setUsersThatVoted(List<SpoqUser> usersThatVoted) {
        this.usersThatVoted = usersThatVoted;
    }

    public void addDownVote(SpoqUser userThatVoted) {
        usersThatVoted.add(userThatVoted);
    }

    public void removeDownVote(SpoqUser userToRemove) {
        Iterator<SpoqUser> setIterator = usersThatVoted.iterator();
        while (setIterator.hasNext()) {
            SpoqUser o = setIterator.next();
            if (o.getMusicServiceId().equals(userToRemove.getMusicServiceId())) {
                setIterator.remove();
            }
        }
    }

    public long getTimeAdded() {
        return timeAdded;
    }

    public void setTimeAdded(long timeAdded) {
        this.timeAdded = timeAdded;
    }
}
