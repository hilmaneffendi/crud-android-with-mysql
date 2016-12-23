package com.coder01.mogumogu.mymahasiswa;

/**
 * Created by MOGU MOGU on 29/07/2016.
 */
public class Config {
    public static final String URL_ADD="http://192.168.173.1/mahasiswa/tambahData.php";
    public static final String URL_GET_ALL = "http://192.168.173.1/mahasiswa/getSemuaData.php";
    public static final String URL_GET_MHS = "http://192.168.173.1/mahasiswa/getData.php?id=";
    public static final String URL_UPDATE_MHS = "http://192.168.173.1/mahasiswa/perbaharuiData.php";
    public static final String URL_DELETE_MHS = "http://192.168.173.1/mahasiswa/hapusData.php?id=";

    //Keys that will be used to send the request to php scripts
    public static final String KEY_MHS_ID = "id";
    public static final String KEY_MHS_NPM = "npm";
    public static final String KEY_MHS_NAMA = "nama_mahasiswa";
    public static final String KEY_MHS_JURUSAN = "jurusan";

    //JSON Tags
    public static final String TAG_JSON_ARRAY="result";
    public static final String TAG_ID = "id";
    public static final String TAG_NPM = "npm";
    public static final String TAG_NAMA = "nama_mahasiswa";
    public static final String TAG_JURUSAN = "jurusan";

    //employee id to pass with intent
    public static final String MHS_ID = "mhs_id";
}
