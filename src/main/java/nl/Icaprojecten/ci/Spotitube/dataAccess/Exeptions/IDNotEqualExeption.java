package nl.Icaprojecten.ci.Spotitube.dataAccess.Exeptions;

public class IDNotEqualExeption extends Exception {
    public IDNotEqualExeption() {
        super("ID is not equal to project that is wanted to edit");
    }

}
