package assig3.UI;

import assig3.filmdata.ERating;
import assig3.filmdata.ESpecialFeatures;

public class EnumPrsss {
	
	public static ERating prsssRating(String i_input) {
		switch (i_input.toLowerCase()) {
		case "pg":
		case "2":
			return ERating.PG;
		case "pg 13":
		case "pg13":
		case "3":
			return ERating.PG_13;
		case "r":
		case "4":
			return ERating.R;
		case "nc 17":
		case "nc17":
		case "5":
			return ERating.NC_17;
		case "g":
		case "1":
		default:
			return ERating.G;
		}
	}
	
	public static  ESpecialFeatures prsssSpecialFeatures(String i_input) {
		switch (i_input.toLowerCase()) {
		case "trailers":
		case "1":
			return ESpecialFeatures.Trailers;
		case "commentaries":
		case "2":
			return ESpecialFeatures.Commentaries;
		case "deletedscenes":
		case "deleted scenes":
		case "3" :
			return ESpecialFeatures.DeletedScenes;
		case "behindthescenes":
		case "behind the scenes":
			return ESpecialFeatures.BehindTheScenes;
		case "no":
		case "n":
		default:
			return null;
		}
	}
}
