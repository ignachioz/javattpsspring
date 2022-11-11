package hibernateDAOS;

import org.springframework.stereotype.Repository;

import genericosDAOS.PostDAO;
import ttps.spring.manguitoClases.Post;

@Repository
public class PostDAOHibernateJPA extends GenericDAOHibernateJPA<Post>  implements PostDAO {

	public PostDAOHibernateJPA() {
		super(Post.class);
		// TODO Auto-generated constructor stub
	}

}
