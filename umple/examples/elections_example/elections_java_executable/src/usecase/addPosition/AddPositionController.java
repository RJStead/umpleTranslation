/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.15.0.1751 modeling language!*/

package usecase.addPosition;
import view.addPosition.AddPositionView;
import shared.Result;
import service.ElectionService;
import service.PositionService;
import service.ElectionForPositionService;
import shared.domain.Election;
import shared.domain.Position;
import shared.domain.ElectionForPosition;
import java.util.List;
import usecase.startup.Controller;

public class AddPositionController
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static AddPositionController theInstance = null;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //AddPositionController Attributes
  private AddPositionView addPositionView;
  private boolean positionAdded;
  private boolean efpAdded;
  private String positionName;
  private Position newPosition;
  private boolean positionFound;
  private ElectionForPosition efp;

  //AddPositionController State Machines
  enum PositionAddingSteps { Initial, PreparingView, CheckingPositionName, CheckingExistingPosition, PositionNameEmpty, PositionExists, AddingPosition, PositionAdded, PositionNotAdded, ClosingView, CheckingpositionName }
  private PositionAddingSteps PositionAddingSteps;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  private AddPositionController()
  {
    positionAdded = false;
    efpAdded = false;
    positionName = null;
    positionFound = false;
    setPositionAddingSteps(PositionAddingSteps.Initial);
  }

  public static AddPositionController getInstance()
  {
    if(theInstance == null)
    {
      theInstance = new AddPositionController();
    }
    return theInstance;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public String getPositionAddingStepsFullName()
  {
    String answer = PositionAddingSteps.toString();
    return answer;
  }

  public PositionAddingSteps getPositionAddingSteps()
  {
    return PositionAddingSteps;
  }

  public boolean addPosition()
  {
    boolean wasEventProcessed = false;
    
    PositionAddingSteps aPositionAddingSteps = PositionAddingSteps;
    switch (aPositionAddingSteps)
    {
      case Initial:
        setPositionAddingSteps(PositionAddingSteps.PreparingView);
        wasEventProcessed = true;
        break;
    }

    return wasEventProcessed;
  }

  public boolean addButtonClicked()
  {
    boolean wasEventProcessed = false;
    
    PositionAddingSteps aPositionAddingSteps = PositionAddingSteps;
    switch (aPositionAddingSteps)
    {
      case PreparingView:
        setPositionAddingSteps(PositionAddingSteps.CheckingPositionName);
        wasEventProcessed = true;
        break;
      case PositionNameEmpty:
        setPositionAddingSteps(PositionAddingSteps.CheckingpositionName);
        wasEventProcessed = true;
        break;
    }

    return wasEventProcessed;
  }

  public boolean closeView()
  {
    boolean wasEventProcessed = false;
    
    PositionAddingSteps aPositionAddingSteps = PositionAddingSteps;
    switch (aPositionAddingSteps)
    {
      case PreparingView:
        setPositionAddingSteps(PositionAddingSteps.ClosingView);
        wasEventProcessed = true;
        break;
      case PositionNameEmpty:
        setPositionAddingSteps(PositionAddingSteps.ClosingView);
        wasEventProcessed = true;
        break;
    }

    return wasEventProcessed;
  }

  private boolean __autotransition37__()
  {
    boolean wasEventProcessed = false;
    
    PositionAddingSteps aPositionAddingSteps = PositionAddingSteps;
    switch (aPositionAddingSteps)
    {
      case CheckingPositionName:
        if (positionName.trim().isEmpty())
        {
          setPositionAddingSteps(PositionAddingSteps.PositionNameEmpty);
          wasEventProcessed = true;
          break;
        }
        break;
    }

    return wasEventProcessed;
  }

  private boolean __autotransition38__()
  {
    boolean wasEventProcessed = false;
    
    PositionAddingSteps aPositionAddingSteps = PositionAddingSteps;
    switch (aPositionAddingSteps)
    {
      case CheckingPositionName:
        if (!positionName.trim().isEmpty())
        {
          setPositionAddingSteps(PositionAddingSteps.CheckingExistingPosition);
          wasEventProcessed = true;
          break;
        }
        break;
    }

    return wasEventProcessed;
  }

  private boolean __autotransition39__()
  {
    boolean wasEventProcessed = false;
    
    PositionAddingSteps aPositionAddingSteps = PositionAddingSteps;
    switch (aPositionAddingSteps)
    {
      case CheckingExistingPosition:
        if (!positionFound)
        {
          setPositionAddingSteps(PositionAddingSteps.AddingPosition);
          wasEventProcessed = true;
          break;
        }
        break;
    }

    return wasEventProcessed;
  }

  private boolean __autotransition40__()
  {
    boolean wasEventProcessed = false;
    
    PositionAddingSteps aPositionAddingSteps = PositionAddingSteps;
    switch (aPositionAddingSteps)
    {
      case CheckingExistingPosition:
        if (positionFound)
        {
          setPositionAddingSteps(PositionAddingSteps.PositionExists);
          wasEventProcessed = true;
          break;
        }
        break;
    }

    return wasEventProcessed;
  }

  private boolean __autotransition41__()
  {
    boolean wasEventProcessed = false;
    
    PositionAddingSteps aPositionAddingSteps = PositionAddingSteps;
    switch (aPositionAddingSteps)
    {
      case PositionExists:
        setPositionAddingSteps(PositionAddingSteps.Initial);
        wasEventProcessed = true;
        break;
    }

    return wasEventProcessed;
  }

  private boolean __autotransition42__()
  {
    boolean wasEventProcessed = false;
    
    PositionAddingSteps aPositionAddingSteps = PositionAddingSteps;
    switch (aPositionAddingSteps)
    {
      case AddingPosition:
        if (positionAdded)
        {
          setPositionAddingSteps(PositionAddingSteps.PositionAdded);
          wasEventProcessed = true;
          break;
        }
        break;
    }

    return wasEventProcessed;
  }

  private boolean __autotransition43__()
  {
    boolean wasEventProcessed = false;
    
    PositionAddingSteps aPositionAddingSteps = PositionAddingSteps;
    switch (aPositionAddingSteps)
    {
      case AddingPosition:
        if (!positionAdded)
        {
          setPositionAddingSteps(PositionAddingSteps.PositionNotAdded);
          wasEventProcessed = true;
          break;
        }
        break;
    }

    return wasEventProcessed;
  }

  private boolean __autotransition44__()
  {
    boolean wasEventProcessed = false;
    
    PositionAddingSteps aPositionAddingSteps = PositionAddingSteps;
    switch (aPositionAddingSteps)
    {
      case PositionAdded:
        setPositionAddingSteps(PositionAddingSteps.Initial);
        wasEventProcessed = true;
        break;
    }

    return wasEventProcessed;
  }

  private boolean __autotransition45__()
  {
    boolean wasEventProcessed = false;
    
    PositionAddingSteps aPositionAddingSteps = PositionAddingSteps;
    switch (aPositionAddingSteps)
    {
      case PositionNotAdded:
        setPositionAddingSteps(PositionAddingSteps.Initial);
        wasEventProcessed = true;
        break;
    }

    return wasEventProcessed;
  }

  private boolean __autotransition46__()
  {
    boolean wasEventProcessed = false;
    
    PositionAddingSteps aPositionAddingSteps = PositionAddingSteps;
    switch (aPositionAddingSteps)
    {
      case ClosingView:
        setPositionAddingSteps(PositionAddingSteps.Initial);
        wasEventProcessed = true;
        break;
    }

    return wasEventProcessed;
  }

  private void setPositionAddingSteps(PositionAddingSteps aPositionAddingSteps)
  {
    PositionAddingSteps = aPositionAddingSteps;

    // entry actions and do activities
    switch(PositionAddingSteps)
    {
      case PreparingView:
        showAddPositionView();
        break;
      case CheckingPositionName:
        positionName=addPositionView.getPositionName();
        __autotransition37__();
        __autotransition38__();
        break;
      case CheckingExistingPosition:
        positionFound=positionExists();
        __autotransition39__();
        __autotransition40__();
        break;
      case PositionNameEmpty:
        Result.getInstance().setMessage("position name cannot be empty!");
        break;
      case PositionExists:
        Result.getInstance().setMessage("position Exists!");
          Controller.getInstance().start();
          addPositionView.dispose();
        __autotransition41__();
        break;
      case AddingPosition:
        tryToAddPosition();
          positionAdded=PositionService.getInstance().getPositionAdded();
          //efpAdded=ElectionForPositionService.getInstance().getElectionForPositionAdded();
        __autotransition42__();
        __autotransition43__();
        break;
      case PositionAdded:
        Result.getInstance().setMessage("Position Added Successfully!");
          Controller.getInstance().start();
        __autotransition44__();
        break;
      case PositionNotAdded:
        Result.getInstance().setMessage("Adding Position Failed!");
          Controller.getInstance().start();
        __autotransition45__();
        break;
      case ClosingView:
        addPositionView.dispose();
        __autotransition46__();
        break;
    }
  }

  public void delete()
  {}


  public void showAddPositionView(){
      addPositionView=new AddPositionView();
     addPositionView.setVisible(true);
  }
  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  private boolean positionExists() {
    //selectedElection=addPositionView.getSelectedElection();
    newPosition=new Position(-1, positionName, "");
    //efp=new ElectionForPosition(-1, selectedElection, newPosition);
    PositionService.getInstance().setPositionToSearch(newPosition);
    return PositionService.getInstance().getPositionFound();
  }
  
  void tryToAddPosition() {
    String positionDescription=addPositionView.getPositionDescription();
    newPosition.setDescription(positionDescription);
    PositionService.getInstance().setNewPosition(newPosition);
    //ElectionForPositionService.getInstance().setNewElectionForPosition(efp);
    addPositionView.dispose();
  }
}