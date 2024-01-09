package com.searchengine.roots;

public class RootFinderFactory {
	

	public RootFinderFactory() {

	}

	public static IRootFinder create(Roots root) {
		IRootFinder rootfinder = null;
		switch (root) {
		case ACTIVE_ROOTS: {
			rootfinder = new ActiveRootFinder();
			break;
		}
		case ANY_ONE_ROOT: {
			rootfinder = new SystemRootFinder();
			break;
		}
		case ALL_ROOTS: {
			rootfinder = new SystemRootFinder();
		}
		default :{
			
		}
		}
		return rootfinder;
	}

}
