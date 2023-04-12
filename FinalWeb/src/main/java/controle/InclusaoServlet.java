package controle;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.Usuario;
import servico.UsuarioServico;

/**
 * Servlet implementation class Inclusao
 */
@WebServlet("/InclusaoServlet")
public class InclusaoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InclusaoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Usuario user = new Usuario();
		HttpSession session = request.getSession();
		
		user.setUsuario(request.getParameter("txtUsuario"));
		user.setNome(request.getParameter("txtNome"));
		user.setSenha(request.getParameter("txtSenha"));
		
		UsuarioServico servico = new UsuarioServico();
		
		if(!servico.verificaUsuarioExiste(user)){
			servico.cadastraUsuario(user);
			List<Usuario> lista = servico.listarUsuario();
			session.setAttribute("lista", lista);
			response.sendRedirect("home.jsp");
		}else {
			response.sendRedirect("inclusao.jsp");
		}
	}

}
