package mp06;

public class ObjectCode {
    private String objFileName;

    public ObjectCode(String objFileName) {
        if(objFileName.contains(".obj")) {
            this.objFileName = objFileName;
            return;
        }

        this.objFileName = null;
    }

    public String getObjFileName() {
        return objFileName;
    }

    public void setObjFileName(String objFileName) {
        this.objFileName = objFileName;
    }

    @Override
    public String toString() {
        return objFileName;
    }
}
