package fr.pau.univ.series.servlets;

import java.io.IOException;

import fr.pau.univ.series.dao.impl.bdd.DaoBddHelper;
import fr.pau.univ.series.exception.DaoException;
import fr.pau.univ.series.model.Episode;
import fr.pau.univ.series.model.Saison;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/creerEpisode")
public class CreerEpisode extends HttpServlet{
	private static final long serialVersionUID = 1L;

	/** 
	 * Le constructeur de la classe.
	 * 
	 * @see HttpServlet#HttpServlet()
	 */
	public CreerEpisode() {
	}

	/**
	 * Méthode qui gère les requêtes GET.
	 * 
	 * @param request La requête HTTP
	 * @param response La réponse HTTP
	 * @throws ServletException Si une erreur de servlet survient
	 * @throws IOException Si une erreur d'entrée/sortie survient
	 */
	@Override
	protected void doPost(final HttpServletRequest request, final HttpServletResponse response)
			throws ServletException, IOException {
		final String nom = request.getParameter("nomNouvelEpisode");
		final String numero = request.getParameter("numeroNouvelEpisode");
		if ((nom != null && !nom.isBlank())&&(numero != null && !numero.isBlank())) {
			try {
				DaoBddHelper.getInstance().addEpisode(new Episode(nom,Integer.parseInt(numero)));
			} catch (final DaoException e) {
				request.setAttribute("erreur", e.getMessage());
			}
		}
		final String redir = "http://".concat(request.getServerName()).concat(":")
				.concat(Integer.toString(request.getServerPort())).concat(request.getContextPath()).concat("/liste");
		response.sendRedirect(redir);
	}

}