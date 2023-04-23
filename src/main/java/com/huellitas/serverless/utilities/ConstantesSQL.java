package com.huellitas.serverless.utilities;

public class ConstantesSQL {

	public static final int DB_TIMEOUT = 7;

	public static final String INSERT_REFUGIO_REGISTRATION_MASIVE = "{? = call  HUELLITAS.PAUSER.FNINSERTREFUGEREGISTRATIONMASIVE(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
    public static final String INSERT_VACCINE_PET = "{? = call  HUELLITAS.PAPET.FNINSERTVACCINEPET(?,?)}";
    public static final String INSERT_FEATURE = "{? = call  HUELLITAS.PAPET.FNINSERTFEATURE(?,?)}";

}
