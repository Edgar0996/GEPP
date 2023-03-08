package Kranon.GEPP.Utileria;
import java.io.Serializable;

/*Author Promotora Kranon
 *Clase Modelo para el envio de Email*/

public class ModelEmail implements Serializable {

    //Variables de ruta de archivos de prueba
    public static String pathConfigurations = "";
    public static String pathFileorigen = "";
    public static String pathFileDestino = "";

    /*Variables que se imprimen en el reporte email*/

    //Variables Reporte
    private String vsFechaReporte;
    private String vsFechaEjecucion;
    private String vsFechaFinEjecucion;

    //Variables Resumen de Descarga de Archivos Fuentes - Listas De Marcacion - RD
    private String vsInicioProcesoRD;
    private String vsConexionSFTPRD;
    private String vsArchEncontradosRD;
    private String vsArchDescargadosRD;
    private String vsArchNoCumplenRD;

    private String vsDetalleArchivosRD;
    private String vsDetalleArchivos2RD;

    //Variables Validacion De Integridad De Datos - Listas De Marcacion - VDI
    private String vsInicioProcesoVDI;
    private String vsArchAnalizadosVDI;
    private String vsArchFinalesVDI;

    private String vsDetalleArchivos1VDI;
    private String vsDetalleArchivosV2DI;

    //Variables Objetos Genesys Cloud - OG
    private String vsInicioProcesoOG;
    private String vsCampaniasOG;
    private String vsListasMarcacionOG;
    private String vsCorrelacionesProcesoOG;

    private String vsCampaniasApagadasOG;
    private String vsListasDescargadasOG;
    private String vsListasLimpiadasOG;
    private String vsArchivosCSVcargadosOG;

    private String vsRevisionContenidoLMOKOG;
    private String vsRevisionContenidoLMERROROG;

    //Detalle de Archivos

    private String vsArchListaCampaniasOG;
    private String vsArchListasMarcacionOG;
    private String vsArchCorrelacionesOG;

    private String vsArchCampaniasApagadasOG;
    private String vsArchListasDescargadasOG;
    private String vsArchistasLimpiadasOG;
    private String vsArchCSVCargadosOG;

    private String vsArchRevisionContenidoLMOKOG;
    private String vsArchRevisionContenidoLMERROROG;

    //Variables Encendido De Campañas GEPP

    private String vsInicioProcesoEC;
    private String vsCampaniasApagadasEC;
    private String vsCampaniasEncendidasEC;


    private String vsArchCampaniasApagadasEC;
    private String vsArchCampaniasEncendidasEC;


    //Variables Envio del Reporte Final --> Dichas variables se toman directo desde la clase de ejecucion <---


    /*Constructores de la clase Vacios - Personalizados*/
    public ModelEmail(){
        //Constructor vacio!
    }

    public String getVsFechaReporte() {
        return vsFechaReporte;
    }

    public void setVsFechaReporte(String vsFechaReporte) {
        this.vsFechaReporte = vsFechaReporte;
    }

    public String getVsFechaEjecucion() {
        return vsFechaEjecucion;
    }

    public void setVsFechaEjecucion(String vsFechaEjecucion) {
        this.vsFechaEjecucion = vsFechaEjecucion;
    }

    public String getVsFechaFinEjecucion() {
        return vsFechaFinEjecucion;
    }

    public void setVsFechaFinEjecucion(String vsFechaFinEjecucion) {
        this.vsFechaFinEjecucion = vsFechaFinEjecucion;
    }

    public String getVsInicioProcesoRD() {
        return vsInicioProcesoRD;
    }

    public void setVsInicioProcesoRD(String vsInicioProcesoRD) {
        this.vsInicioProcesoRD = vsInicioProcesoRD;
    }

    public String getVsConexionSFTPRD() {
        return vsConexionSFTPRD;
    }

    public void setVsConexionSFTPRD(String vsConexionSFTPRD) {
        this.vsConexionSFTPRD = vsConexionSFTPRD;
    }

    public String getVsArchEncontradosRD() {
        return vsArchEncontradosRD;
    }

    public void setVsArchEncontradosRD(String vsArchEncontradosRD) {
        this.vsArchEncontradosRD = vsArchEncontradosRD;
    }

    public String getVsArchDescargadosRD() {
        return vsArchDescargadosRD;
    }

    public void setVsArchDescargadosRD(String vsArchDescargadosRD) {
        this.vsArchDescargadosRD = vsArchDescargadosRD;
    }

    public String getVsArchNoCumplenRD() {
        return vsArchNoCumplenRD;
    }

    public void setVsArchNoCumplenRD(String vsArchNoCumplenRD) {
        this.vsArchNoCumplenRD = vsArchNoCumplenRD;
    }

    public String getVsDetalleArchivosRD() {
        return vsDetalleArchivosRD;
    }

    public void setVsDetalleArchivosRD(String vsDetalleArchivosRD) {
        this.vsDetalleArchivosRD = vsDetalleArchivosRD;
    }

    public String getVsDetalleArchivos2RD() {
        return vsDetalleArchivos2RD;
    }

    public void setVsDetalleArchivos2RD(String vsDetalleArchivos2RD) {
        this.vsDetalleArchivos2RD = vsDetalleArchivos2RD;
    }

    public String getVsInicioProcesoVDI() {
        return vsInicioProcesoVDI;
    }

    public void setVsInicioProcesoVDI(String vsInicioProcesoVDI) {
        this.vsInicioProcesoVDI = vsInicioProcesoVDI;
    }

    public String getVsArchAnalizadosVDI() {
        return vsArchAnalizadosVDI;
    }

    public void setVsArchAnalizadosVDI(String vsArchAnalizadosVDI) {
        this.vsArchAnalizadosVDI = vsArchAnalizadosVDI;
    }

    public String getVsArchFinalesVDI() {
        return vsArchFinalesVDI;
    }

    public void setVsArchFinalesVDI(String vsArchFinalesVDI) {
        this.vsArchFinalesVDI = vsArchFinalesVDI;
    }

    public String getVsDetalleArchivos1VDI() {
        return vsDetalleArchivos1VDI;
    }

    public void setVsDetalleArchivos1VDI(String vsDetalleArchivos1VDI) {
        this.vsDetalleArchivos1VDI = vsDetalleArchivos1VDI;
    }

    public String getVsDetalleArchivosV2DI() {
        return vsDetalleArchivosV2DI;
    }

    public void setVsDetalleArchivosV2DI(String vsDetalleArchivosV2DI) {
        this.vsDetalleArchivosV2DI = vsDetalleArchivosV2DI;
    }

    public String getVsInicioProcesoOG() {
        return vsInicioProcesoOG;
    }

    public void setVsInicioProcesoOG(String vsInicioProcesoOG) {
        this.vsInicioProcesoOG = vsInicioProcesoOG;
    }

    public String getVsCampaniasOG() {
        return vsCampaniasOG;
    }

    public void setVsCampaniasOG(String vsCampaniasOG) {
        this.vsCampaniasOG = vsCampaniasOG;
    }

    public String getVsListasMarcacionOG() {
        return vsListasMarcacionOG;
    }

    public void setVsListasMarcacionOG(String vsListasMarcacionOG) {
        this.vsListasMarcacionOG = vsListasMarcacionOG;
    }

    public String getVsCorrelacionesProcesoOG() {
        return vsCorrelacionesProcesoOG;
    }

    public void setVsCorrelacionesProcesoOG(String vsCorrelacionesProcesoOG) {
        this.vsCorrelacionesProcesoOG = vsCorrelacionesProcesoOG;
    }

    public String getVsCampaniasApagadasOG() {
        return vsCampaniasApagadasOG;
    }

    public void setVsCampaniasApagadasOG(String vsCampaniasApagadasOG) {
        this.vsCampaniasApagadasOG = vsCampaniasApagadasOG;
    }

    public String getVsListasDescargadasOG() {
        return vsListasDescargadasOG;
    }

    public void setVsListasDescargadasOG(String vsListasDescargadasOG) {
        this.vsListasDescargadasOG = vsListasDescargadasOG;
    }

    public String getVsListasLimpiadasOG() {
        return vsListasLimpiadasOG;
    }

    public void setVsListasLimpiadasOG(String vsListasLimpiadasOG) {
        this.vsListasLimpiadasOG = vsListasLimpiadasOG;
    }

    public String getVsArchivosCSVcargadosOG() {
        return vsArchivosCSVcargadosOG;
    }

    public void setVsArchivosCSVcargadosOG(String vsArchivosCSVcargadosOG) {
        this.vsArchivosCSVcargadosOG = vsArchivosCSVcargadosOG;
    }

    public String getVsRevisionContenidoLMOKOG() {
        return vsRevisionContenidoLMOKOG;
    }

    public void setVsRevisionContenidoLMOKOG(String vsRevisionContenidoLMOKOG) {
        this.vsRevisionContenidoLMOKOG = vsRevisionContenidoLMOKOG;
    }

    public String getVsRevisionContenidoLMERROROG() {
        return vsRevisionContenidoLMERROROG;
    }

    public void setVsRevisionContenidoLMERROROG(String vsRevisionContenidoLMERROROG) {
        this.vsRevisionContenidoLMERROROG = vsRevisionContenidoLMERROROG;
    }

    public String getVsArchListaCampaniasOG() {
        return vsArchListaCampaniasOG;
    }

    public void setVsArchListaCampaniasOG(String vsArchListaCampaniasOG) {
        this.vsArchListaCampaniasOG = vsArchListaCampaniasOG;
    }

    public String getVsArchListasMarcacionOG() {
        return vsArchListasMarcacionOG;
    }

    public void setVsArchListasMarcacionOG(String vsArchListasMarcacionOG) {
        this.vsArchListasMarcacionOG = vsArchListasMarcacionOG;
    }

    public String getVsArchCorrelacionesOG() {
        return vsArchCorrelacionesOG;
    }

    public void setVsArchCorrelacionesOG(String vsArchCorrelacionesOG) {
        this.vsArchCorrelacionesOG = vsArchCorrelacionesOG;
    }

    public String getVsArchCampaniasApagadasOG() {
        return vsArchCampaniasApagadasOG;
    }

    public void setVsArchCampaniasApagadasOG(String vsArchCampaniasApagadasOG) {
        this.vsArchCampaniasApagadasOG = vsArchCampaniasApagadasOG;
    }

    public String getVsArchListasDescargadasOG() {
        return vsArchListasDescargadasOG;
    }

    public void setVsArchListasDescargadasOG(String vsArchListasDescargadasOG) {
        this.vsArchListasDescargadasOG = vsArchListasDescargadasOG;
    }

    public String getVsArchistasLimpiadasOG() {
        return vsArchistasLimpiadasOG;
    }

    public void setVsArchistasLimpiadasOG(String vsArchistasLimpiadasOG) {
        this.vsArchistasLimpiadasOG = vsArchistasLimpiadasOG;
    }

    public String getVsArchCSVCargadosOG() {
        return vsArchCSVCargadosOG;
    }

    public void setVsArchCSVCargadosOG(String vsArchCSVCargadosOG) {
        this.vsArchCSVCargadosOG = vsArchCSVCargadosOG;
    }

    public String getVsArchRevisionContenidoLMOKOG() {
        return vsArchRevisionContenidoLMOKOG;
    }

    public void setVsArchRevisionContenidoLMOKOG(String vsArchRevisionContenidoLMOKOG) {
        this.vsArchRevisionContenidoLMOKOG = vsArchRevisionContenidoLMOKOG;
    }

    public String getVsArchRevisionContenidoLMERROROG() {
        return vsArchRevisionContenidoLMERROROG;
    }

    public void setVsArchRevisionContenidoLMERROROG(String vsArchRevisionContenidoLMERROROG) {
        this.vsArchRevisionContenidoLMERROROG = vsArchRevisionContenidoLMERROROG;
    }

    public String getVsInicioProcesoEC() {
        return vsInicioProcesoEC;
    }

    public void setVsInicioProcesoEC(String vsInicioProcesoEC) {
        this.vsInicioProcesoEC = vsInicioProcesoEC;
    }

    public String getVsCampaniasApagadasEC() {
        return vsCampaniasApagadasEC;
    }

    public void setVsCampaniasApagadasEC(String vsCampaniasApagadasEC) {
        this.vsCampaniasApagadasEC = vsCampaniasApagadasEC;
    }

    public String getVsCampaniasEncendidasEC() {
        return vsCampaniasEncendidasEC;
    }

    public void setVsCampaniasEncendidasEC(String vsCampaniasEncendidasEC) {
        this.vsCampaniasEncendidasEC = vsCampaniasEncendidasEC;
    }

    public String getVsArchCampaniasApagadasEC() {
        return vsArchCampaniasApagadasEC;
    }

    public void setVsArchCampaniasApagadasEC(String vsArchCampaniasApagadasEC) {
        this.vsArchCampaniasApagadasEC = vsArchCampaniasApagadasEC;
    }

    public String getVsArchCampaniasEncendidasEC() {
        return vsArchCampaniasEncendidasEC;
    }

    public void setVsArchCampaniasEncendidasEC(String vsArchCampaniasEncendidasEC) {
        this.vsArchCampaniasEncendidasEC = vsArchCampaniasEncendidasEC;
    }

    /**Construccion del metodo ToString() --> para la impresion de informacion */
    @Override
    public String toString() {
        return "ModelEmail{" +
                "vsFechaReporte='" + vsFechaReporte + '\'' +
                ", vsFechaEjecucion='" + vsFechaEjecucion + '\'' +
                ", vsFechaFinEjecucion='" + vsFechaFinEjecucion + '\'' +
                ", vsInicioProcesoRD='" + vsInicioProcesoRD + '\'' +
                ", vsConexionSFTPRD='" + vsConexionSFTPRD + '\'' +
                ", vsArchEncontradosRD='" + vsArchEncontradosRD + '\'' +
                ", vsArchDescargadosRD='" + vsArchDescargadosRD + '\'' +
                ", vsArchNoCumplenRD='" + vsArchNoCumplenRD + '\'' +
                ", vsDetalleArchivosRD='" + vsDetalleArchivosRD + '\'' +
                ", vsDetalleArchivos2RD='" + vsDetalleArchivos2RD + '\'' +
                ", vsInicioProcesoVDI='" + vsInicioProcesoVDI + '\'' +
                ", vsArchAnalizadosVDI='" + vsArchAnalizadosVDI + '\'' +
                ", vsArchFinalesVDI='" + vsArchFinalesVDI + '\'' +
                ", vsDetalleArchivos1VDI='" + vsDetalleArchivos1VDI + '\'' +
                ", vsDetalleArchivosV2DI='" + vsDetalleArchivosV2DI + '\'' +
                ", vsInicioProcesoOG='" + vsInicioProcesoOG + '\'' +
                ", vsCampaniasOG='" + vsCampaniasOG + '\'' +
                ", vsListasMarcacionOG='" + vsListasMarcacionOG + '\'' +
                ", vsCorrelacionesProcesoOG='" + vsCorrelacionesProcesoOG + '\'' +
                ", vsCampaniasApagadasOG='" + vsCampaniasApagadasOG + '\'' +
                ", vsListasDescargadasOG='" + vsListasDescargadasOG + '\'' +
                ", vsListasLimpiadasOG='" + vsListasLimpiadasOG + '\'' +
                ", vsArchivosCSVcargadosOG='" + vsArchivosCSVcargadosOG + '\'' +
                ", vsRevisionContenidoLMOKOG='" + vsRevisionContenidoLMOKOG + '\'' +
                ", vsRevisionContenidoLMERROROG='" + vsRevisionContenidoLMERROROG + '\'' +
                ", vsArchListaCampaniasOG='" + vsArchListaCampaniasOG + '\'' +
                ", vsArchListasMarcacionOG='" + vsArchListasMarcacionOG + '\'' +
                ", vsArchCorrelacionesOG='" + vsArchCorrelacionesOG + '\'' +
                ", vsArchCampaniasApagadasOG='" + vsArchCampaniasApagadasOG + '\'' +
                ", vsArchListasDescargadasOG='" + vsArchListasDescargadasOG + '\'' +
                ", vsArchistasLimpiadasOG='" + vsArchistasLimpiadasOG + '\'' +
                ", vsArchCSVCargadosOG='" + vsArchCSVCargadosOG + '\'' +
                ", vsArchRevisionContenidoLMOKOG='" + vsArchRevisionContenidoLMOKOG + '\'' +
                ", vsArchRevisionContenidoLMERROROG='" + vsArchRevisionContenidoLMERROROG + '\'' +
                ", vsInicioProcesoEC='" + vsInicioProcesoEC + '\'' +
                ", vsCampaniasApagadasEC='" + vsCampaniasApagadasEC + '\'' +
                ", vsCampaniasEncendidasEC='" + vsCampaniasEncendidasEC + '\'' +
                ", vsArchCampaniasApagadasEC='" + vsArchCampaniasApagadasEC + '\'' +
                ", vsArchCampaniasEncendidasEC='" + vsArchCampaniasEncendidasEC + '\'' +
                '}';
    }
}
