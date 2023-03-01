package Kranon.GEPP.Local;
import com.opencsv.bean.CsvBindAndSplitByName;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;

public class ModelListaMarcacion {

    //Apuntamos hacia la cabecera dando el nombre de la cabecera, requiered para que sea obligatorio

    @CsvBindByName(column = "inin-outbound-id")
    private String inin_outbound_id = "";
    @CsvBindByName(column = "idBodega" , required = false)
    private String idBodega = "";
    @CsvBindByName(column = "nud" , required = false)
    private String nud = "";
    @CsvBindByName(column = "NOMBRE", required = false)
    private String nombre = "";
    @CsvBindByName(column = "TELUNO" , required = false)
    private String TELUNO = "";
    @CsvBindByName(column = "TIPOTELUNO" , required = false)
    private String tipoteluno = "";
    @CsvBindByName(column = "TELDOS" , required = false)
    private String teldos = "";
    @CsvBindByName(column = "TIPOTELDOS" , required = false)
    private String tipoteldos = "";
    @CsvBindByName(column = "TELTRES" , required = false)
    private String teltres = "";
    @CsvBindByName(column = "TIPOTELTRES" , required = false)
    private String tipoteltres = "";
    @CsvBindByName(column = "TELCUATRO" , required = false)
    private String telcuatro = "";
    @CsvBindByName(column = "TIPOTELCUATRO" , required = false)
    private String tipotelcuatro = "";
    @CsvBindByName(column = "FRECUENCIA" , required = false)
    private String frecuencia = "";
    @CsvBindByName(column = "CORREO" , required = false)
    private String correo = "";
    @CsvBindByName(column = "CONTACTO" , required = false)
    private String contacto = "";
    @CsvBindByName(column = "ruta" , required = false)
    private String ruta = "";
    @CsvBindByName(column = "token" , required = false)
    private String token = "";
    @CsvBindByName(column = "DIASINCOMPRA")
    private String diasincompra = "";
    @CsvBindByName(column = "marcador" )
    private String marcador = "";
    @CsvBindByName(column = "appId" , required = false)
    private String appId = "";
    @CsvBindByName(column = "opcion" , required = false)
    private String opcion = "";
    @CsvBindByName(column = "uq" , required = false)
    private String uq = "";
    @CsvBindByName(column = "INTENTOS_NUMERO_UNO")
    private String intentoUno = "";
    @CsvBindByName(column =  "INTENTOS_NUMERO_DOS")
    private String intentodos = "";
    @CsvBindByName(column =  "")
    private String intentotres = "INTENTOS_NUMERO_TRES";
    @CsvBindByName(column =  "INTENTOS_NUMERO_CUATRO")
    private String intentocuatro = "";
    @CsvBindByName(column =  "INTENTOS_TOTAL")
    private String intentostotal = "";
    @CsvBindByName(column =  "ContactCallable")
    private String contactCallable = "";
    @CsvBindByName(column =  "ContactableByVoice")
    private String contactableByVoice = "";
    @CsvBindByName(column =  "ContactableBySms")
    private String contactableBySms = "";
    @CsvBindByName(column =  "ContactableByEmail")
    private String contactableByEmail = "";
    @CsvBindByName(column =  "ZipCodeAutomaticTimeZone")
    private String zipCodeAutomaticTimeZone = "";
    @CsvBindByName(column =  "CallRecordLastAttempt-TELUNO")
    private String  callRecordLastAttemptTELUNO = "";
    @CsvBindByName(column =  "CallRecordLastResult-TELUNO")
    private String callRecordLastResultTELUNO = "";
    @CsvBindByName(column =  "CallRecordLastAgentWrapup-TELUNO")
    private String CallRecordLastAgentWrapupTELUNO = "";
    @CsvBindByName(column =  "SmsLastAttempt-TELUNO")
    private String  SmsLastAttemptTELUNO= "";
    @CsvBindByName(column =  "SmsLastResult-TELUNO")
    private String  SmsLastResultTELUNO= "";
    @CsvBindByName(column =  "Callable-TELUNO")
    private String  CallableTELUNO= "";
    @CsvBindByName(column =  "ContactableByVoice-TELUNO")
    private String ContactableByVoiceTELUNO = "";
    @CsvBindByName(column =  "ContactableBySms-TELUNO")
    private String ContactableBySmsTELUNO = "";
    @CsvBindByName(column =  "AutomaticTimeZone-TELUNO")
    private String  AutomaticTimeZoneTELUNO= "";
    @CsvBindByName(column =  "CallRecordLastAttempt-TELDOS")
    private String CallRecordLastAttemptTELDOS = "";
    @CsvBindByName(column =  "CallRecordLastResult-TELDOS")
    private String  CallRecordLastResultTELDOS = "";
    @CsvBindByName(column =  "CallRecordLastAgentWrapup-TELDOS")
    private String  CallRecordLastAgentWrapupTELDOS = "";
    @CsvBindByName(column =  "SmsLastAttempt-TELDOS")
    private String SmsLastAttemptTELDOS = "";
    @CsvBindByName(column =  "SmsLastResult-TELDOS")
    private String SmsLastResultTELDOS = "";
    @CsvBindByName(column =  "Callable-TELDOS")
    private String CallableTELDOS = "";
    @CsvBindByName(column =  "ContactableByVoice-TELDOS")
    private String ContactableByVoiceTELDOS = "";
    @CsvBindByName(column =  "ContactableBySms-TELDOS")
    private String  ContactableBySmsTELDOS = "";
    @CsvBindByName(column =  "AutomaticTimeZone-TELDOS")
    private String AutomaticTimeZoneTELDOS = "";


    public String getInin_outbound_id() {
        return inin_outbound_id;
    }

    public void setInin_outbound_id(String inin_outbound_id) {
        this.inin_outbound_id = inin_outbound_id;
    }

    public String getIdBodega() {
        return idBodega;
    }

    public void setIdBodega(String idBodega) {
        this.idBodega = idBodega;
    }

    public String getNud() {
        return nud;
    }

    public void setNud(String nud) {
        this.nud = nud;
    }

    public String getTELUNO() {
        return TELUNO;
    }

    public void setTELUNO(String TELUNO) {
        this.TELUNO = TELUNO;
    }

    public String getTipoteluno() {
        return tipoteluno;
    }

    public void setTipoteluno(String tipoteluno) {
        this.tipoteluno = tipoteluno;
    }

    public String getTeldos() {
        return teldos;
    }

    public void setTeldos(String teldos) {
        this.teldos = teldos;
    }

    public String getTipoteldos() {
        return tipoteldos;
    }

    public void setTipoteldos(String tipoteldos) {
        this.tipoteldos = tipoteldos;
    }

    public String getTeltres() {
        return teltres;
    }

    public void setTeltres(String teltres) {
        this.teltres = teltres;
    }

    public String getTipoteltres() {
        return tipoteltres;
    }

    public void setTipoteltres(String tipoteltres) {
        this.tipoteltres = tipoteltres;
    }

    public String getTelcuatro() {
        return telcuatro;
    }

    public void setTelcuatro(String telcuatro) {
        this.telcuatro = telcuatro;
    }

    public String getTipotelcuatro() {
        return tipotelcuatro;
    }

    public void setTipotelcuatro(String tipotelcuatro) {
        this.tipotelcuatro = tipotelcuatro;
    }

    public String getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(String frecuencia) {
        this.frecuencia = frecuencia;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getDiasincompra() {
        return diasincompra;
    }

    public void setDiasincompra(String diasincompra) {
        this.diasincompra = diasincompra;
    }

    public String getMarcador() {
        return marcador;
    }

    public void setMarcador(String marcador) {
        this.marcador = marcador;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getOpcion() {
        return opcion;
    }

    public void setOpcion(String opcion) {
        this.opcion = opcion;
    }

    public String getUq() {
        return uq;
    }

    public void setUq(String uq) {
        this.uq = uq;
    }

    public String getIntentoUno() {
        return intentoUno;
    }

    public void setIntentoUno(String intentoUno) {
        this.intentoUno = intentoUno;
    }

    public String getIntentodos() {
        return intentodos;
    }

    public void setIntentodos(String intentodos) {
        this.intentodos = intentodos;
    }

    public String getIntentotres() {
        return intentotres;
    }

    public void setIntentotres(String intentotres) {
        this.intentotres = intentotres;
    }

    public String getIntentocuatro() {
        return intentocuatro;
    }

    public void setIntentocuatro(String intentocuatro) {
        this.intentocuatro = intentocuatro;
    }

    public String getIntentostotal() {
        return intentostotal;
    }

    public void setIntentostotal(String intentostotal) {
        this.intentostotal = intentostotal;
    }

    public String getContactCallable() {
        return contactCallable;
    }

    public void setContactCallable(String contactCallable) {
        this.contactCallable = contactCallable;
    }

    public String getContactableByVoice() {
        return contactableByVoice;
    }

    public void setContactableByVoice(String contactableByVoice) {
        this.contactableByVoice = contactableByVoice;
    }

    public String getContactableBySms() {
        return contactableBySms;
    }

    public void setContactableBySms(String contactableBySms) {
        this.contactableBySms = contactableBySms;
    }

    public String getContactableByEmail() {
        return contactableByEmail;
    }

    public void setContactableByEmail(String contactableByEmail) {
        this.contactableByEmail = contactableByEmail;
    }

    public String getZipCodeAutomaticTimeZone() {
        return zipCodeAutomaticTimeZone;
    }

    public void setZipCodeAutomaticTimeZone(String zipCodeAutomaticTimeZone) {
        this.zipCodeAutomaticTimeZone = zipCodeAutomaticTimeZone;
    }

    public String getCallRecordLastAttemptTELUNO() {
        return callRecordLastAttemptTELUNO;
    }

    public void setCallRecordLastAttemptTELUNO(String callRecordLastAttemptTELUNO) {
        this.callRecordLastAttemptTELUNO = callRecordLastAttemptTELUNO;
    }

    public String getCallRecordLastResultTELUNO() {
        return callRecordLastResultTELUNO;
    }

    public void setCallRecordLastResultTELUNO(String callRecordLastResultTELUNO) {
        this.callRecordLastResultTELUNO = callRecordLastResultTELUNO;
    }

    public String getCallRecordLastAgentWrapupTELUNO() {
        return CallRecordLastAgentWrapupTELUNO;
    }

    public void setCallRecordLastAgentWrapupTELUNO(String callRecordLastAgentWrapupTELUNO) {
        CallRecordLastAgentWrapupTELUNO = callRecordLastAgentWrapupTELUNO;
    }

    public String getSmsLastAttemptTELUNO() {
        return SmsLastAttemptTELUNO;
    }

    public void setSmsLastAttemptTELUNO(String smsLastAttemptTELUNO) {
        SmsLastAttemptTELUNO = smsLastAttemptTELUNO;
    }

    public String getSmsLastResultTELUNO() {
        return SmsLastResultTELUNO;
    }

    public void setSmsLastResultTELUNO(String smsLastResultTELUNO) {
        SmsLastResultTELUNO = smsLastResultTELUNO;
    }

    public String getCallableTELUNO() {
        return CallableTELUNO;
    }

    public void setCallableTELUNO(String callableTELUNO) {
        CallableTELUNO = callableTELUNO;
    }

    public String getContactableByVoiceTELUNO() {
        return ContactableByVoiceTELUNO;
    }

    public void setContactableByVoiceTELUNO(String contactableByVoiceTELUNO) {
        ContactableByVoiceTELUNO = contactableByVoiceTELUNO;
    }

    public String getContactableBySmsTELUNO() {
        return ContactableBySmsTELUNO;
    }

    public void setContactableBySmsTELUNO(String contactableBySmsTELUNO) {
        ContactableBySmsTELUNO = contactableBySmsTELUNO;
    }

    public String getAutomaticTimeZoneTELUNO() {
        return AutomaticTimeZoneTELUNO;
    }

    public void setAutomaticTimeZoneTELUNO(String automaticTimeZoneTELUNO) {
        AutomaticTimeZoneTELUNO = automaticTimeZoneTELUNO;
    }

    public String getCallRecordLastAttemptTELDOS() {
        return CallRecordLastAttemptTELDOS;
    }

    public void setCallRecordLastAttemptTELDOS(String callRecordLastAttemptTELDOS) {
        CallRecordLastAttemptTELDOS = callRecordLastAttemptTELDOS;
    }

    public String getCallRecordLastResultTELDOS() {
        return CallRecordLastResultTELDOS;
    }

    public void setCallRecordLastResultTELDOS(String callRecordLastResultTELDOS) {
        CallRecordLastResultTELDOS = callRecordLastResultTELDOS;
    }

    public String getCallRecordLastAgentWrapupTELDOS() {
        return CallRecordLastAgentWrapupTELDOS;
    }

    public void setCallRecordLastAgentWrapupTELDOS(String callRecordLastAgentWrapupTELDOS) {
        CallRecordLastAgentWrapupTELDOS = callRecordLastAgentWrapupTELDOS;
    }

    public String getSmsLastAttemptTELDOS() {
        return SmsLastAttemptTELDOS;
    }

    public void setSmsLastAttemptTELDOS(String smsLastAttemptTELDOS) {
        SmsLastAttemptTELDOS = smsLastAttemptTELDOS;
    }

    public String getSmsLastResultTELDOS() {
        return SmsLastResultTELDOS;
    }

    public void setSmsLastResultTELDOS(String smsLastResultTELDOS) {
        SmsLastResultTELDOS = smsLastResultTELDOS;
    }

    public String getCallableTELDOS() {
        return CallableTELDOS;
    }

    public void setCallableTELDOS(String callableTELDOS) {
        CallableTELDOS = callableTELDOS;
    }

    public String getContactableByVoiceTELDOS() {
        return ContactableByVoiceTELDOS;
    }

    public void setContactableByVoiceTELDOS(String contactableByVoiceTELDOS) {
        ContactableByVoiceTELDOS = contactableByVoiceTELDOS;
    }

    public String getContactableBySmsTELDOS() {
        return ContactableBySmsTELDOS;
    }

    public void setContactableBySmsTELDOS(String contactableBySmsTELDOS) {
        ContactableBySmsTELDOS = contactableBySmsTELDOS;
    }

    public String getAutomaticTimeZoneTELDOS() {
        return AutomaticTimeZoneTELDOS;
    }

    public void setAutomaticTimeZoneTELDOS(String automaticTimeZoneTELDOS) {
        AutomaticTimeZoneTELDOS = automaticTimeZoneTELDOS;
    }

    @Override
    public String toString() {
        return "ModelListaMarcacion{" +
                "inin_outbound_id='" + inin_outbound_id + '\'' +
                ", idBodega='" + idBodega + '\'' +
                ", nud='" + nud + '\'' +
                ", nombre='" + nombre + '\'' +
                ", TELUNO='" + TELUNO + '\'' +
                ", tipoteluno='" + tipoteluno + '\'' +
                ", teldos='" + teldos + '\'' +
                ", tipoteldos='" + tipoteldos + '\'' +
                ", teltres='" + teltres + '\'' +
                ", tipoteltres='" + tipoteltres + '\'' +
                ", telcuatro='" + telcuatro + '\'' +
                ", tipotelcuatro='" + tipotelcuatro + '\'' +
                ", frecuencia='" + frecuencia + '\'' +
                ", correo='" + correo + '\'' +
                ", contacto='" + contacto + '\'' +
                ", ruta='" + ruta + '\'' +
                ", token='" + token + '\'' +
                ", diasincompra='" + diasincompra + '\'' +
                ", marcador='" + marcador + '\'' +
                ", appId='" + appId + '\'' +
                ", opcion='" + opcion + '\'' +
                ", uq='" + uq + '\'' +
                ", intentoUno='" + intentoUno + '\'' +
                ", intentodos='" + intentodos + '\'' +
                ", intentotres='" + intentotres + '\'' +
                ", intentocuatro='" + intentocuatro + '\'' +
                ", intentostotal='" + intentostotal + '\'' +
                ", contactCallable='" + contactCallable + '\'' +
                ", contactableByVoice='" + contactableByVoice + '\'' +
                ", contactableBySms='" + contactableBySms + '\'' +
                ", contactableByEmail='" + contactableByEmail + '\'' +
                ", zipCodeAutomaticTimeZone='" + zipCodeAutomaticTimeZone + '\'' +
                ", callRecordLastAttemptTELUNO='" + callRecordLastAttemptTELUNO + '\'' +
                ", callRecordLastResultTELUNO='" + callRecordLastResultTELUNO + '\'' +
                ", CallRecordLastAgentWrapupTELUNO='" + CallRecordLastAgentWrapupTELUNO + '\'' +
                ", SmsLastAttemptTELUNO='" + SmsLastAttemptTELUNO + '\'' +
                ", SmsLastResultTELUNO='" + SmsLastResultTELUNO + '\'' +
                ", CallableTELUNO='" + CallableTELUNO + '\'' +
                ", ContactableByVoiceTELUNO='" + ContactableByVoiceTELUNO + '\'' +
                ", ContactableBySmsTELUNO='" + ContactableBySmsTELUNO + '\'' +
                ", AutomaticTimeZoneTELUNO='" + AutomaticTimeZoneTELUNO + '\'' +
                ", CallRecordLastAttemptTELDOS='" + CallRecordLastAttemptTELDOS + '\'' +
                ", CallRecordLastResultTELDOS='" + CallRecordLastResultTELDOS + '\'' +
                ", CallRecordLastAgentWrapupTELDOS='" + CallRecordLastAgentWrapupTELDOS + '\'' +
                ", SmsLastAttemptTELDOS='" + SmsLastAttemptTELDOS + '\'' +
                ", SmsLastResultTELDOS='" + SmsLastResultTELDOS + '\'' +
                ", CallableTELDOS='" + CallableTELDOS + '\'' +
                ", ContactableByVoiceTELDOS='" + ContactableByVoiceTELDOS + '\'' +
                ", ContactableBySmsTELDOS='" + ContactableBySmsTELDOS + '\'' +
                ", AutomaticTimeZoneTELDOS='" + AutomaticTimeZoneTELDOS + '\'' +
                '}';
    }
}


