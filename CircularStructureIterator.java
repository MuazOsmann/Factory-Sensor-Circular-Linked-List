import java.util.Iterator;

public class CircularStructureIterator implements Iterator<Sensor> {

    /*************************************************************
     * Customized iterator for the CircularStructure
     */

    CircularStructure.Node current;
    CircularStructure circularStructure;

    public CircularStructureIterator(CircularStructure circularStructure){
        this.circularStructure = circularStructure;
        this.current = circularStructure.getHead();
    }
    @Override
    public boolean hasNext() {
        return this.current != null;
    }

    /********************************************************************
     * Overrides the default next method to avoid infinite iteration.
     * @return
     */
    @Override
    public Sensor next() {
        Sensor sensor = this.current.sensor;

        if(current == circularStructure.getTail())
            this.current = null;
        else
            this.current = this.current.next;
        return sensor;
    }
}
