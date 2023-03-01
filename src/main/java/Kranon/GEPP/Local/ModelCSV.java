package Kranon.GEPP.Local;

import java.io.File;

public class ModelCSV {

    /**Variables  */
    private int viNumCabeceras = 0;
    private String vsRutaLectura = "";
    private String vsRutaDestino = "";
    private File file = new File(vsRutaLectura);
    private File fileDestino  = new File(vsRutaDestino);


    public int getViNumCabeceras() {
        return viNumCabeceras;
    }

    public void setViNumCabeceras(int viNumCabeceras) {
        this.viNumCabeceras = viNumCabeceras;
    }

    public String getVsRutaLectura() {
        return vsRutaLectura;
    }

    public void setVsRutaLectura(String vsRutaLectura) {
        this.vsRutaLectura = vsRutaLectura;
    }

    public String getVsRutaDestino() {
        return vsRutaDestino;
    }

    public void setVsRutaDestino(String vsRutaDestino) {
        this.vsRutaDestino = vsRutaDestino;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public File getFileDestino() {
        return fileDestino;
    }

    public void setFileDestino(File fileDestino) {
        this.fileDestino = fileDestino;
    }
}
