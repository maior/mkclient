package mkii.mkblock.client.common;

public class Configure {

    public String dirPath;
    public String sourceFolder;
    public String lang;
    public String projectName;

    public String ip;
    public String port;
    public String publicKey;
    public String target;
    public String compile;
    public String contract;
    public String taxi;

    public Configure(String publicKey, String target, String dirPath, String sourceFolder, String lang, String projectName, String contract, String compile, String ip, String port, String taxi) {
        this.publicKey = publicKey;
        this.target = target;
        this.dirPath = dirPath;
        this.sourceFolder = sourceFolder;
        this.lang = lang;
        this.projectName = projectName;
        this.contract = contract;
        this.compile = compile;
        this.ip = ip;
        this.port = port;
        this.taxi = taxi;
    }
}
