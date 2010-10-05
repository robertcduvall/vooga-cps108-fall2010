
Our Level system is a portable and simple-structured level system so that it can be easily applied to different engines. 
 This is achieved by using the Manager interface which has the core methods for the API. The Level class is simplified to be a 
 container of other objects of other API's such as Game State objects, Player/Item objects and resources.

The implementation of our example reflected this design. The bouncingBall class implements the FANGLevelManager and the Levels class implements
 the Level class. In general, our design is simple and robust enough so that we think this can be smoothly merged with other systems
 in general. 

The class bouncingBall extends FANGLevelManager and uses many of FANG's built in methods to build the levels.  It initializes 20 Levels, which extends Level of our API.  FANGLevelManager builds and houses all the levels, but each level is an extension of our general Level class.