package com.example.antiwaste;

import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.antiwaste.Entity.Adresse;
import com.example.antiwaste.Entity.Commercant;
import com.example.antiwaste.Entity.Panier;
import com.example.antiwaste.Entity.Utilisateur;
import com.example.antiwaste.dao.AdresseRepository;
import com.example.antiwaste.dao.CommercantRepository;
import com.example.antiwaste.dao.PanierRepository;
import com.example.antiwaste.dao.UtilisateurRepository;
import com.example.antiwaste.service.AdresseService;
import com.example.antiwaste.service.CommercantService;
import com.example.antiwaste.service.PanierService;
import com.example.antiwaste.service.UtilisateurService;

@SpringBootApplication
public class AntiWasteApplication implements CommandLineRunner {

	@Autowired
	private UtilisateurRepository ur;
	
	@Autowired
	private AdresseRepository ar;
	@Autowired 
	private AdresseService as;
	
	@Autowired
	private CommercantRepository cr;
	@Autowired
	private CommercantService cs;
	
	@Autowired
	private PanierRepository pr;
	@Autowired
	private PanierService ps;
	
	@Autowired 
	private UtilisateurService us;
	
	public static void main(String[] args) {
		SpringApplication.run(AntiWasteApplication.class, args);
	
	
	
	}
	@Override
	public void run(String... args) throws Exception {
		
		
		
		
		Adresse a1=  as.saveAdresse(new Adresse (6,"Rue Gaspard Coriolis","37200", "Tours","France"));
		Adresse a2= as.saveAdresse(new Adresse (7,"Avenue de la pain","48900", "Akbou","Algérie"));
		Adresse a3= as.saveAdresse(new Adresse (65,"Lotissement Rayan","06001", "Akbou","Algérie"));
		Adresse a4= as.saveAdresse(new Adresse (1,"Rue du 1er Novembre","06001", "Akbou","Algérie"));
		Adresse a5= as.saveAdresse(new Adresse (1,"Rue Targa ouzemour","37200", "Béjaia","Algérie"));
		Adresse a6= as.saveAdresse(new Adresse (128,"Rue de Guendouza","06001", "Akbou","Algérie"));
		
		
		ur.save(new Utilisateur ("abdelouhab","aimad","utilisateur@email.com","motdepasse","+33775436980"));
		
		String logo="https://www.thonescoeurdesvallees.com/wp-content/uploads/wpetourisme/9014787-diaporama.jpg";
		
		Commercant cb= cs.saveCommercant(new Commercant("Boulangerie Marville","boulangerie","Fabiola et Damien Marville vous accueillent dans leur boulangerie où l'on aime se rendre, car elle fait partie des meilleures pour dénicher une bonne baguette",logo,a1));
	
		
		cs.saveCommercant(new Commercant("Epicerie du coin","epicerie","description","https://i.pinimg.com/600x315/5b/18/49/5b184980732ce9de2150eec156496bde.jpg",a2));
		Commercant ce=cs.saveCommercant(new Commercant("Le Zingam","epicerie","description","https://media.timeout.com/images/105633861/320/210/image.jpg",a3));
		cs.saveCommercant(new Commercant("Le 38 Gourmet","epicerie","description","http://img.over-blog.com/500x372/0/11/18/70/IMGP4378.JPG",a4));
		
		
		Commercant cbo=cs.saveCommercant(new Commercant("La Boucherie","boucherie","description","https://www.lagrandeepicerie.com/dw/image/v2/BBWG_PRD/on/demandware.static/-/Library-Sites-LGEsharedLibrary/default/dwee256232/LGE_V3/MAGASINS/UNIVERS/boucherie/boucherie-rive-gauche.jpg",a1));
		cs.saveCommercant(new Commercant("1001 Délices","boucherie","description","https://epiceries.imgix.net/shop_images/323886/original/155924_0006_f_1595610400.jpg?ixlib=rails-4.2.0&auto=format%2Ccompress&fit=crop&crop=faces&ch=Width,DPR&h=375",a2));
		
		Commercant cp= cs.saveCommercant(new Commercant("Le marché","primeur","description","https://images.ollca.com/fit-in/528x367/ollca/7df3f247-b895-4032-b6f4-a51efcf86ff8/shop/ac04d6f6-b2ec-4480-8f7c-04e331c5a9ef/1614176403.jpg",a3));
		cs.saveCommercant(new Commercant("Los Leguminos","primeur","description","https://images.ollca.com/fit-in/528x367/ollca/7df3f247-b895-4032-b6f4-a51efcf86ff8/shop/59c51211-805b-4e77-a5a7-8b4786188015/1614176402.jpg",a4));
		
		
		
		
		System.out.println(cs.commercantParCategorie());
		System.out.println(cs.commercantParCategorie().size());
		
		
		Date aujourdhui = new Date();

	    DateFormat shortDateFormat = DateFormat.getDateTimeInstance(
	        DateFormat.SHORT,
	        DateFormat.SHORT);
	    
	    LocalDateTime myDateObj = LocalDateTime.now();
	    LocalDateTime myDateObj2 = LocalDateTime.of(2021,07,07,15,40);
	    LocalDateTime myDateObj3 = LocalDateTime.of(2021,07,07,14,50);
	    LocalDateTime myDateObj4 = LocalDateTime.of(2021,07,8,13,40);
	    LocalDateTime myDateObj5 = LocalDateTime.of(2021,07,07,20,40);
	    LocalDateTime myDateObj6 = LocalDateTime.of(2021,07,8,13,40);
	    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

	    String formattedDate = myDateObj.format(myFormatObj);
	    
	    String legumes="https://res.cloudinary.com/hv9ssmzrz/image/fetch/c_fill,f_auto,h_360,q_auto,w_740/https://s3-eu-west-1.amazonaws.com/images-ca-1-0-1-eu/tag_photos/original/15/legumes-flickr-6018347885_010e2ffbb5_o.jpg";
	    String fruits="https://www.unlockfood.ca/EatRightOntario/media/Website-images-resized/How-to-store-fruit-to-keep-it-fresh-resized.jpg";
	    String croissant="https://scally.typepad.com/.a/6a00d8341c676f53ef01901cce2c05970b-pi";
	    String lehwal ="https://lh3.googleusercontent.com/proxy/zH2P5FifUqaE-jJavLO_jMSnaaHFt8SqSLJe0V0IsTberbLfbF98jSb6_5gHBVaBMkT5osd-oQGAg-k3DEHexUbroIMC9FWjnTZPaq4yGShdqlPfLxcWhvz8cAPl_v9_";
	    String le3dess="https://santecool.net/wp-content/uploads/2019/02/ab.jpg";
	    String gazouz="https://doucefrugalite.files.wordpress.com/2020/06/les-sodas.jpg";
	    
	    String viande="https://images.theconversation.com/files/350975/original/file-20200804-18-vk0jr7.jpg?ixlib=rb-1.1.0&rect=44%2C269%2C5000%2C2500&q=45&auto=format&w=668&h=324&fit=crop";
	    String bouzelouf="https://i.pinimg.com/originals/3a/2e/7b/3a2e7b58d5b242b40c4e566dd1866d81.jpg";
	    String poulet="https://img.cuisineaz.com/680x357/2015/10/14/i48315-recettes-pour-une-chicken-party.jpg";
	    String helouf="https://static.pourdebon.com/images/476-355/10ea3d3c881a524fc8e3731da0bcc0a6/roti_porc_1kg.jpeg";

	    
	    

	    Panier p1=ps.savePanier(new Panier("Panier de gourmandides","viénoiserie","Panier de 5 croissant et 5 pains au chocolat",croissant,myDateObj2,1,10,cb));
		
		ps.savePanier(new Panier("Panier d'épices","variété","description",lehwal,myDateObj2,3,15,ce));		
		ps.savePanier(new Panier("Panier de légumes secs","lentilles","description",le3dess,myDateObj3,3,52,ce));		
		ps.savePanier(new Panier("Panier de boissons","viande","decription",gazouz,myDateObj4,2,20,ce));
		
		ps.savePanier(new Panier("Panier de viandes","viandes","decription",viande,myDateObj5,4,9.99,cbo));
		ps.savePanier(new Panier("Panier de bouzelouf","viandes","decription",bouzelouf,myDateObj6,2,12,cbo));
		ps.savePanier(new Panier("Panier de charcuterie","viandes","decription",poulet,myDateObj2,12,13,cbo));
		ps.savePanier(new Panier("Panier de volaille","volaille","decription",helouf,myDateObj3,1,20,cbo));
		
		ps.savePanier(new Panier("Panier de fruits","fruit","decription",fruits,myDateObj4,2,19.99,cp));
		ps.savePanier(new Panier("Panier de légumes","legumes","decription",legumes,myDateObj5,1,22,cp));
		
		System.out.println(ps.panierParCategorie());
		System.out.println(ps.panierParCategorie().size());
		
		System.out.println(ps.commercantParPanier(5));

		List<Utilisateur> ul = ur.findAll();
		Utilisateur u = ul.get(0);
		ce.getUtilisateurs().add(u);
		cr.save(ce);
		Optional<Utilisateur> uo = ur.findById(1L);
		System.out.println(2);
		
		//us.reserverPanier(u, p1, 1);
		
		//System.out.println(us.listeDesReservations(u).get(0).getNombreExemplairesReserves());
		us.listeDesReservations(u)	;
		/*
		 * Utilisateur u2=ur.save(new Utilisateur
		 * ("nom2","prenom2","utilisateur2@email.com","mot de passe2","numero2"));
		 * Utilisateur u = ur.findById((long)1).get();
		 * 
		 * System.out.println(u.getEmail()); u.setNom("aziz");
		 * u.setPrenom("copperfield"); ur.save(u); ur.delete(u2);
		 */
	}
	
	

}
