package org.cruise.umple.eclipse.plugin.wizards;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.dialogs.WizardNewProjectCreationPage;

public class NusmvBaseUmpleProjectWizard extends Wizard implements INewWizard {

	private WizardNewProjectCreationPage fPage;
	
	@Override
	public void addPages() {
		// TODO Auto-generated method stub
		super.addPages();
		fPage = new WizardNewProjectCreationPage("Create NuSMV-based umple project ");
		fPage.setTitle("NuSMV-based Umple Project");
		fPage.setDescription("This project creates an Umple project with NuSMV features.");
		addPage(fPage);
	}
	
	public NusmvBaseUmpleProjectWizard() {
		setWindowTitle("Umple Project Creator");
	}

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean performFinish() {
		// TODO Auto-generated method stub
		return true;
	}

}
