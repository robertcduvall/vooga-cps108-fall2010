# XML Format For Composite Objects #



All the game objects inside a level are outlined inside the tag 

&lt;LevelObjects&gt;

 and all the rules inside the level are stored inside the 

&lt;LevelRules&gt;

. Please add to this format and all the attributes that would make a good level XML.


# Details #

```
<?xml version="1.0" encoding="UTF-8" ?>
<Level name="Level1">

	<SpriteGroups>
		<SpriteGroup name="playerGroup">
			<RegularSprite name="playerSprite" x="0" y="100" vx="10" vy="20" q="1">
				<Visual name="Player!!!" />
			</RegularSprite>
			<MySprite name="playerName" xRange="0" yRange="100" vxValue="10" health="100" damage="15">
				<Visual name="mySpriteImage" />
			</MySprite>
		</SpriteGroup>
		<SpriteGroup name="enemyGroup">
			<SpawnedSprite name="enemySprite" xRange="300" yRange="400" vxRange="10" vyRange="-10" quantity="20">
				<Visual name="Enemy!!!" />
			</SpawnedSprite>
		</SpriteGroup>
	</SpriteGroups>

	<CollisionGroups>
		<CollisionGroup subclass="vooga.examples.factory.PlayerEnemyCollision">
			<SpriteGroup name="playerGroup"></SpriteGroup>
			<SpriteGroup name="enemyGroup"></SpriteGroup>
		</CollisionGroup>
	</CollisionGroups>
	
	<Background>
		<Visual name ="background1" />
		<Visual name ="background2" />
		<Visual name ="background3" />
	</Background>
	
	<Music>
		<Sound name ="music1" />
		<Sound name ="music2" />
		<Sound name ="music3" />
	</Music>
	
	<Map name="Map">
		<MapGroup name = "firstMap" />
			<Association char = "G" object = "IndestructibleTile"></Association>
	</Map>
</Level>
```