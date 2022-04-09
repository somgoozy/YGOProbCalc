import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
public class CardProperties {
	String path = "cardinfo.json";
	String name;
	Set<TYPE> type = new HashSet<>();
	Set<ATTRIBUTE> attribute = new HashSet<>();
	Set<PROPERTIES> properties = new HashSet<>();
	Set<ARROWS> arrows = new HashSet<>();
	int level;
	int attack;
	int defense;
	int pendScale;
	int id;
	int linkRating;
	//lists of card properties
	public enum TYPE {
		//spell and trap type
		CONTINUOUS,
		COUNTER,
		FIELD,
		EQUIP,
		NORMAL,
		QUICK_PLAY,
		RITUAL,
		//monster type
		AQUA,
		BEAST,
		BEAST_WARRIOR,
		CREATOR_GOD,
		CYBERSE,
		DINOSAUR,
		DIVINE_BEAST,
		DRAGON,
		FAIRY,
		FIEND,
		FISH,
		INSECT,
		MACHINE,
		PLANT,
		PSYCHIC,
		PYRO,
		REPTILE,
		ROCK,
		SEA_SERPENT,
		SPELLCASTER,
		THUNDER,
		WARRIOR,
		WINGED_BEAST
	}
	public enum ATTRIBUTE {
		//monster attribute
		DARK,
		DIVINE,
		EARTH,
		FIRE,
		LIGHT,
		WATER,
		WIND
	}
	public enum PROPERTIES {
		//card type
		MONSTER,
		SPELL,
		TRAP,
		//monster properties
		EFFECT,
		FLIP,
		GEMINI,
		NORMAL,
		PENDULUM,
		RITUAL,
		SPIRIT,
		TOON,
		TOKEN,
		TUNER,
		UNION,
		//extra deck types
		FUSION,
		LINK,
		SYNCHRO,
		XYZ
	}
	public enum ARROWS {
		UP,
		UP_RIGHT,
		RIGHT,
		DOWN_RIGHT,
		DOWN,
		DOWN_LEFT,
		LEFT,
		UP_LEFT
	}
	private static final Map<String, Set<PROPERTIES>> prop_map = Map.ofEntries(
		//maps api strings to sets of property enums
		Map.entry("effect monster", Set.of(PROPERTIES.MONSTER, PROPERTIES.EFFECT)),
		Map.entry("flip effect monster", Set.of(PROPERTIES.MONSTER, PROPERTIES.EFFECT, PROPERTIES.FLIP)),
		Map.entry("flip tuner effect monster", Set.of(PROPERTIES.MONSTER, PROPERTIES.EFFECT, PROPERTIES.FLIP, PROPERTIES.TUNER)),
		Map.entry("gemini monster", Set.of(PROPERTIES.MONSTER, PROPERTIES.GEMINI)),
		Map.entry("normal monster", Set.of(PROPERTIES.MONSTER, PROPERTIES.NORMAL)),
		Map.entry("normal tuner monster", Set.of(PROPERTIES.MONSTER, PROPERTIES.NORMAL, PROPERTIES.TUNER)),
		Map.entry("pendulum effect monster", Set.of(PROPERTIES.MONSTER, PROPERTIES.PENDULUM, PROPERTIES.EFFECT)),
		Map.entry("pendulum flip effect monster", Set.of(PROPERTIES.MONSTER, PROPERTIES.PENDULUM, PROPERTIES.EFFECT, PROPERTIES.FLIP)),
		Map.entry("pendulum normal monster", Set.of(PROPERTIES.MONSTER, PROPERTIES.PENDULUM, PROPERTIES.NORMAL)),
		Map.entry("pendulum tuner effect monster", Set.of(PROPERTIES.MONSTER, PROPERTIES.PENDULUM, PROPERTIES.EFFECT, PROPERTIES.TUNER)),
		Map.entry("ritual effect monster", Set.of(PROPERTIES.MONSTER, PROPERTIES.RITUAL, PROPERTIES.EFFECT)),
		Map.entry("ritual monster", Set.of(PROPERTIES.MONSTER, PROPERTIES.RITUAL)),
		Map.entry("spell card", Set.of(PROPERTIES.SPELL)),
		Map.entry("spirit monster", Set.of(PROPERTIES.MONSTER, PROPERTIES.EFFECT, PROPERTIES.SPIRIT)),
		Map.entry("toon monster", Set.of(PROPERTIES.MONSTER, PROPERTIES.EFFECT, PROPERTIES.TOON)),
		Map.entry("trap card", Set.of(PROPERTIES.TRAP)),
		Map.entry("tuner monster", Set.of(PROPERTIES.MONSTER, PROPERTIES.TUNER)),
		Map.entry("union effect monster", Set.of(PROPERTIES.MONSTER, PROPERTIES.EFFECT, PROPERTIES.UNION)),
		Map.entry("fusion monster", Set.of(PROPERTIES.MONSTER, PROPERTIES.FUSION)),
		Map.entry("link monster", Set.of(PROPERTIES.MONSTER, PROPERTIES.LINK)),
		Map.entry("pendulum effect fusion monster", Set.of(PROPERTIES.MONSTER, PROPERTIES.PENDULUM, PROPERTIES.EFFECT, PROPERTIES.FUSION)),
		Map.entry("synchro monster", Set.of(PROPERTIES.MONSTER, PROPERTIES.FUSION)),
		Map.entry("synchro pendulum effect monster", Set.of(PROPERTIES.MONSTER, PROPERTIES.SYNCHRO, PROPERTIES.PENDULUM, PROPERTIES.EFFECT)),
		Map.entry("synchro tuner monster", Set.of(PROPERTIES.MONSTER, PROPERTIES.SYNCHRO, PROPERTIES.TUNER)),
		Map.entry("xyz monster", Set.of(PROPERTIES.MONSTER, PROPERTIES.XYZ)),
		Map.entry("xyz pendulum effect monster", Set.of(PROPERTIES.MONSTER, PROPERTIES.XYZ, PROPERTIES.PENDULUM, PROPERTIES.EFFECT))
	);
	private static final Map<String, TYPE> type_map = Map.ofEntries(
		//maps api strings to type enums	
		Map.entry("aqua", TYPE.AQUA),
		Map.entry("beast", TYPE.BEAST),
		Map.entry("beast-warrior", TYPE.BEAST_WARRIOR),
		Map.entry("creator-god", TYPE.CREATOR_GOD),
		Map.entry("cyberse", TYPE.CYBERSE),
		Map.entry("dinosaur", TYPE.DINOSAUR),
		Map.entry("divine-beast", TYPE.DIVINE_BEAST),
		Map.entry("dragon", TYPE.DRAGON),
		Map.entry("fairy", TYPE.FAIRY),
		Map.entry("fiend", TYPE.FIEND),
		Map.entry("fish", TYPE.FISH),
		Map.entry("insect", TYPE.INSECT),
		Map.entry("machine", TYPE.MACHINE),
		Map.entry("plant", TYPE.PLANT),
		Map.entry("psychic", TYPE.PSYCHIC),
		Map.entry("pyro", TYPE.PYRO),
		Map.entry("reptile", TYPE.REPTILE),
		Map.entry("rock", TYPE.ROCK),
		Map.entry("sea serpent", TYPE.SEA_SERPENT),
		Map.entry("spellcaster", TYPE.SPELLCASTER),
		Map.entry("thunder", TYPE.THUNDER),
		Map.entry("warrior", TYPE.WARRIOR),
		Map.entry("winged beast", TYPE.WINGED_BEAST),
		Map.entry("normal", TYPE.NORMAL),
		Map.entry("field", TYPE.FIELD),
		Map.entry("equip", TYPE.EQUIP),
		Map.entry("continuous", TYPE.CONTINUOUS),
		Map.entry("counter", TYPE.COUNTER),
		Map.entry("puick-play", TYPE.QUICK_PLAY),
		Map.entry("ritual", TYPE.RITUAL)
	);
	private static final Map<String, ATTRIBUTE> attribute_map = Map.ofEntries(
		//maps api strings to attribute enums
		Map.entry("DARK", ATTRIBUTE.DARK),
		Map.entry("DIVINE", ATTRIBUTE.DIVINE),
		Map.entry("EARTH", ATTRIBUTE.EARTH),
		Map.entry("FIRE", ATTRIBUTE.FIRE),
		Map.entry("LIGHT", ATTRIBUTE.LIGHT),
		Map.entry("WATER", ATTRIBUTE.WATER),
		Map.entry("WIND", ATTRIBUTE.WIND)
	);
	private static final Map<String, ARROWS> arrow_map = Map.ofEntries(
		//maps api strings to arrow enums
		Map.entry("Top", ARROWS.UP),
		Map.entry("Top-Right", ARROWS.UP_RIGHT),
		Map.entry("Right", ARROWS.RIGHT),
		Map.entry("Bottom-Right", ARROWS.DOWN_RIGHT),
		Map.entry("Bottom", ARROWS.DOWN),
		Map.entry("Bottom-Left", ARROWS.DOWN_LEFT),
		Map.entry("Left", ARROWS.LEFT),
		Map.entry("Top-Left", ARROWS.UP_LEFT)		
	);
	public CardProperties(String name) {
		findData(name);
	}
	public CardProperties(int id) {
		findData(id);
	}
	private <T> void findData(T identifier) {
		//read json and make tree
				File jsonInputFile = new File(path);
				ObjectMapper mapper = new ObjectMapper();
				JsonNode rootNode;
				try {
					rootNode = mapper.readTree(jsonInputFile);
					//traverse to data
					JsonNode data = rootNode.path("data");
					//create iterator to loop
					Iterator<JsonNode> itr = data.elements();
					//loop looking for name
					while(itr.hasNext()) {
			            JsonNode cardData = itr.next();
			            //if found
			            if(identifier.getClass() == Integer.class) {
			            	if(cardData.path("id").asInt() == (int)identifier) {
			            		//fill in the data and break the loop
			            		fill(cardData);
			            		break;
			            	}
			            } else if(cardData.path("name").asText().toLowerCase().equals(identifier.toString().toLowerCase())) {
			            	//fill in the data and break the loop
			            	fill(cardData);
			            	break;
			            }
			        }
					if(this.name == null) {
					//what do I do if not found?
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
	}
	private void fill (JsonNode card_node) {
		//set name
		this.name = card_node.path("name").asText();
		//set id
		this.id = card_node.path("id").asInt();
		//set card type and some basic properties
		this.properties.addAll(prop_map.get(card_node.path("type").asText().toLowerCase()));
		//set type
		this.type.add(type_map.get(card_node.path("race").asText().toLowerCase()));
		//if monster card
		if (this.properties.contains(PROPERTIES.MONSTER)) {
			//set attribute
			this.attribute.add(attribute_map.get(card_node.path("attribute").asText().toLowerCase()));
			//set attack
			this.attack = card_node.path("atk").asInt();
			//if not a link
			if(!this.properties.contains(PROPERTIES.LINK)) {
				//set level
				this.level = card_node.path("level").asInt();
				//set defense
				this.defense = card_node.path("def").asInt();
			}
			//if link
			else {
				//get link rating
				this.linkRating = card_node.path("linkval").asInt();
				//create iterator to loop through link markers
				Iterator<JsonNode> itr = card_node.path("linkmarkers").elements();
				//loop looking for link marker data
				while(itr.hasNext()) {
		            //get next element
					JsonNode marker = itr.next();
		            //store marker
					this.arrows.add(arrow_map.get(marker.asText().toLowerCase()));
				}
			}
			if(!this.properties.contains(PROPERTIES.PENDULUM)) {
				this.pendScale = card_node.path("scale").asInt();
			}
		}
	}
}