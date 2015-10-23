import javax.swing.*;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by domen on 3/14/15.
 * Stores one animation.
 */
public class Animation implements ListModel {

    private List<ListDataListener> listDataListeners = new ArrayList<ListDataListener>();
    private List<Frame> frames = new ArrayList<Frame>();

    private int number;

    public Animation(int n) {
        number = n;
        addFrame();
    }

    public Frame getFrame(int i) {
        return frames.get(i);
    }

    public void addFrame() {
        frames.add(new Frame(number));
        sendListDataEvent(new ListDataEvent(this, ListDataEvent.INTERVAL_ADDED, frames.size()-1, frames.size()-1));
    }

    public void duplicateFrame(int i) {
        if (i >= 0) {
            frames.add(new Frame(frames.get(i)));
            sendListDataEvent(new ListDataEvent(this, ListDataEvent.INTERVAL_ADDED, frames.size()-1, frames.size()-1));
        }
    }

    public void deleteFrame(int i) {
        if (i >= 0) {
            frames.remove(i);
            sendListDataEvent(new ListDataEvent(this, ListDataEvent.INTERVAL_REMOVED, i, i));
        }
    }

    public void moveDown(int i) {
        if (i >= 0 && i < frames.size()-1) {
            Frame tmp = frames.get(i);
            frames.set(i, frames.get(i+1));
            frames.set(i+1, tmp);
            sendListDataEvent(new ListDataEvent(this, ListDataEvent.CONTENTS_CHANGED, i, i+1));
        }
    }

    public void moveUp(int i) {
        if (i > 0) {
            Frame tmp = frames.get(i);
            frames.set(i, frames.get(i-1));
            frames.set(i-1, tmp);
            sendListDataEvent(new ListDataEvent(this, ListDataEvent.CONTENTS_CHANGED, i-1, i));
        }
    }

    public void renameFrame(int i, String n) {
        frames.get(i).setName(n);
    }

    protected void sendListDataEvent(ListDataEvent lde) {
        for (ListDataListener ldl : listDataListeners) {
            switch(lde.getType()) {
                case ListDataEvent.CONTENTS_CHANGED:
                    ldl.contentsChanged(lde);
                    break;
                case ListDataEvent.INTERVAL_ADDED:
                    ldl.intervalAdded(lde);
                    break;
                case ListDataEvent.INTERVAL_REMOVED:
                    ldl.intervalRemoved(lde);
                    break;
            }
        }
    }

    @Override
    public int getSize() {
        return frames.size();
    }

    @Override
    public Object getElementAt(int i) {
        return frames.get(i).getName();
    }

    @Override
    public void addListDataListener(ListDataListener listDataListener) {
        listDataListeners.add(listDataListener);
    }

    @Override
    public void removeListDataListener(ListDataListener listDataListener) {
        listDataListeners.remove(listDataListener);
    }
}
