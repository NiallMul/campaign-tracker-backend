# **Campaign Tracker**

## **Project Overview**

The **Campaign Tracker** is a web-based application designed to manage and track ongoing Warhammer campaigns, whether Warhammer 40K or Warhammer Fantasy. Players can log battle results, control territories, and manage strategic resources in a dynamic, evolving campaign setting. The system allows for multiple users, customizable factions, and real-time updates of campaign progress.

### Key Features:
- **Player and Faction Management**: Create and manage players and their corresponding factions.
- **Territory Control**: Track the control and status of different territories as the campaign progresses.
- **Battle Results Logging**: Players can log their battle outcomes, which automatically affect the state of the campaign.
- **Campaign Turn Progression**: Manage and display the current state of the campaign, including resource collection and strategic moves.
- **Dynamic Map Visualization**: See the current campaign status via a visual representation of territorial control.
- **Time-Based Events**: Set events such as resource generation or automatic campaign advancements.

## **Technologies Used**
- **Backend**: Spring Boot (Java 21)
- **Frontend**: AngularJS
- **Database**: MySQL or PostgreSQL (with Hibernate JPA for ORM)
- **Authentication**: Spring Security (JWT-based)
- **Real-time updates**: WebSockets for live campaign updates
- **Version Control**: Git

---

## **Best Practice Branching Strategy**

This project follows **Git Flow** for its branching strategy, ensuring that all development processes are organized, efficient, and scalable. Below are the core branches and workflow:

### **Main Branches:**
1. **`main`**:
   - This branch contains production-ready code. Only thoroughly tested, stable versions are merged here.
   - Deployment to production is done from this branch.
  
2. **`develop`**:
   - This is the primary branch where active development happens. All new features, bug fixes, and improvements are merged here from feature branches.
   - This branch contains the latest code that is ready for testing but may not be ready for production.

### **Supporting Branches:**
1. **`feature/*`**:
   - Used for developing new features. Each new feature is developed in its own branch and is named after the feature being worked on.
   - Example: `feature/add-login-functionality`
   - When the feature is completed and tested locally, it’s merged into the `develop` branch.
   
2. **`hotfix/*`**:
   - Hotfix branches are used to fix critical bugs in the `main` branch that need to be addressed immediately.
   - Once the hotfix is applied, it should be merged into both `main` and `develop` to ensure that the fix is also in the next release.

3. **`release/*`**:
   - This branch is used for preparing a new release.
   - Once a release branch is created, only bug fixes, documentation updates, and final polishing are done here.
   - When the release is ready, it is merged into `main` and tagged, and also merged back into `develop`.
