import java.util.List;
import java.util.Scanner;

import controller.ListPetHelper;
import model.ListPet;

public class ProgramDriver {

	static Scanner in = new Scanner(System.in);
	static ListPetHelper lph = new ListPetHelper();

	private static void addPet() {
		System.out.print("Enter a pet type (cat, dog, etc.): ");
		String type = in.nextLine();
		System.out.print("Enter the pet's name: ");
		String name = in.nextLine();
		System.out.print("Enter the owner's name: ");
		String owner = in.nextLine();
		ListPet toAdd = new ListPet(type, name, owner);
		lph.insertPet(toAdd);
	}
	
	private static void deletePet() {
		System.out.print("Enter the pet type to delete: ");
		String type = in.nextLine();
		System.out.print("Enter the name of the pet to delete: ");
		String name = in.nextLine();
		System.out.print("Enter the name of the owner to delete: ");
		String owner = in.nextLine();
		ListPet toDelete = new ListPet(type, name, owner);
		lph.deletePets(toDelete);
	}
	
	private static void editPet() {
		// TODO Auto-generated method stub
		System.out.println("What method would you like to use you like to search? ");
		System.out.println("-> [1] - Search by Type");
		System.out.println("-> [2] - Search by Name");
		System.out.println("-> [3] - Search by Owner");
		int searchBy = in.nextInt();
		in.nextLine();
		List<ListPet> foundPets;
		
		if (searchBy == 1) {
			System.out.print("Enter the pet's type (cat, dog, etc.): ");
			String type = in.nextLine();
			foundPets = lph.searchForPetByType(type);
		} else if (searchBy == 2) {
			System.out.print("Enter the pet's name: ");
			String name = in.nextLine();
			foundPets = lph.searchForPetByName(name);
		} else {
			System.out.print("Enter the owner's name: ");
			String owner = in.nextLine();
			foundPets = lph.searchForPetByOwner(owner);
		}

		if (!foundPets.isEmpty()) {
			System.out.println("Results found.");
			
			for (ListPet l : foundPets) {
				System.out.println(l.getId() + " : " + l.toString());
			}
			
			System.out.print("Select an ID to edit: ");
			int id = in.nextInt();

			ListPet toEdit = lph.searchForPetById(id);
			System.out.println("Retrieved " + toEdit.getName() + ", a " + toEdit.getType() + " owned by " + toEdit.getOwner());
			System.out.println("-> [1] - Update Type");
			System.out.println("-> [2] - Update Name");
			System.out.println("-> [3] - Update Owner");
			int update = in.nextInt();
			in.nextLine();

			if (update == 1) {
				System.out.print("New Type: ");
				String newType = in.nextLine();
				toEdit.setType(newType);
			} else if (update == 2) {
				System.out.print("New Name: ");
				String newName = in.nextLine();
				toEdit.setName(newName);
			} else if (update == 3) {
				System.out.print("New Owner: ");
				String newOwner = in.nextLine();
				toEdit.setOwner(newOwner);
			}

			lph.updatePet(toEdit);

		} else {
			System.out.println("---- No results found. ----");
		}

	}
	
	private static void viewList() {
		// TODO Auto-generated method stub
		List<ListPet> allPets = lph.showAllPets();
		for(ListPet singleItem : allPets) {
			System.out.println(singleItem.returnPetDetails());
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		boolean display = true;
		
		System.out.println("Welcome! This is a program where you can access a database containing information about local pet owners!");
		System.out.println("------------------------");
		System.out.println(" ");
		
		while (display) {
			System.out.println("-  Make a selection!  -");
			System.out.println("-> [1] - Add a Pet and Owner");
			System.out.println("-> [2] - Edit an Pet or Owner");
			System.out.println("-> [3] - Delete a Pet or Owner");
			System.out.println("-> [4]- View the list of local pets/owners");
			System.out.println("-> [5] - Exit");
			System.out.print("Selection: ");
			
			int selection = in.nextInt();
			in.nextLine();

			if (selection == 1) {
				addPet();
			} else if (selection == 2) {
				editPet();
			} else if (selection == 3) {
				deletePet();
			} else if (selection == 4) {
				viewList();
			} else if (selection == 5) {
				lph.cleanUp();
				System.out.println(" Quiting... ");
				display = false;
			} else {
				System.out.println("Invalid input!");
			}
		}
	}
}
