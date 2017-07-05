package easii.com.br.iscoolapp.constantes;

/**
 * Created by gustavo on 25/10/2016.
 */
public class URL {

    public static final String URL_LOGAR_RESPONSAVEL = "http://" + Constante.ipConnection + "responsavel/login";

    public static final String URL_LOGAR = "http://" + Constante.ipConnection + "usuarioAndroid/login";
    //public static final String URL_LOGAR = "http://" + Constante.ipConnection + ":1234/ServePHP/logarprofessorestatico.php";
    public static final String URL_ESQUECEU = "http://" + Constante.ipConnection  ;

    public static final String URL_LISTAR_PROVAS = "http://" + Constante.ipConnection + "provaAndroid/listar";
    //public static final String URL_LISTAR_PROVAS = "http://" + Constante.ipConnection + ":1234/ServePHP/listarprovas.php";
    public static final String URL_LISTAR_PROVAS_ALUNOS = "http://" + Constante.ipConnection + ":1234/ServePHP/listarProvas2.php";

    public static final String URL_LISTAR_USUARIOS = "http://" + Constante.ipConnection + "usuarioAndroid/lista";


    public static final String URL_LISTAR_FILHOS= "http://" + Constante.ipConnection + "responsavel/listarFilhos";
    //public static final String URL_LISTAR_FILHOS= "http://192.168.0.102/ServePHP/pailistafilhos.php";


    public static final String URL_LISTAR_DISCIPLINA_FILHOS= "http://" + Constante.ipConnection + "responsavel/listaDisciplinas";

   // public static final String URL_LISTAR_DISCIPLINA_FILHOS= "http://192.168.0.102/ServePHP/pailistadisciplina.php";



    public static final String URL_LISTAR_NOTA_FILHOS= "http://" + Constante.ipConnection + "responsavel/listaNotas";

    //public static final String URL_LISTAR_NOTA_FILHOS= "http://192.168.0.102/ServePHP/painotas.php";



    //public static final String URL_LISTAR_USUARIOS = "http://" + Constante.ipConnection + ":1234/ServePHP/listarAlunos.php";

    public static final String SEND_URL_FOTO = "http://" + Constante.ipConnection + "correcao/uploadedImagem";
    //public static final String SEND_URL_FOTO = "http://" + Constante.ipConnection + ":8080/isCoolMensagem/uploadServlet";

    public static final String URL_EXIBIR_NOTA_ALUNO = "http://" + Constante.ipConnection + "notaAndroid/exibirNota";
    // public static final String URL_EXIBIR_NOTA_ALUNO = "http://" + Constante.ipConnection + ":1234/ServePHP/retornaNota.php";

    //public static final String REGISTER_URL = "http://"+ Constante.ipConnection+":1234/ServePHP/adiciona.php";

    public static final String REGISTER_URL = "http://" + Constante.ipConnection + "usuarioAndroid/atualizarToken";

    public static final String SEND_PUSH_MSG = "http://" + Constante.ipConnection + "mensagem/enviarMensagem";

    public static final String SEND_MEDIA = "http://" + Constante.ipConnection + "notaAndroid/graficoMediaTurma";

    public static final String SEND_MEDIA_ALUNO = "http://" + Constante.ipConnection + "notaAndroid/graficoMediaAluno";

    public static final String GET_INSIGNIAS = "http://" + Constante.ipConnection + "insignia/listarInsigniasPorAluno";

    public static final String URL_LISTAR_USUARIOS_RANK = "http://" + Constante.ipConnection + "gamification/listaXPPorProva";

    public static final String URL_LISTAR_USUARIOS_RANK_DISCIPLINA = "http://" + Constante.ipConnection + "gamification/listaXPPorDisciplina";

    public static final String URL_MUDA_PRIVACIDADE = "http://" + Constante.ipConnection + "usuarioAndroid/alterarPrivacidade";

    public static final String URL_ENVIA_EXPLICACAO = "http://" + Constante.ipConnection + "mensagem/explicacao/";


}
