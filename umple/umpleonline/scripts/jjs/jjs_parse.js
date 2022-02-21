var JJSdiagram = {
	associationIndex: 0,
	transitionIndex: 0,
	newClassIndex: 0,
	paper: null,
	container: null,
	// model represents the JSON model
	JSONmodel: null,
	diagram_type: null,

	initJJSDiagram: function (container, model) {
		//clear all tools
		Page.unselectAllToggleTools();

		this.container = container;
		this.JSONmodel = model;

		//initialize paper for jjs
		container.html('<div id="jjsPaper"></div>');

		var graph = new joint.dia.Graph;

		// Start by making the paper an arbitrary size; it will later be re-scaled to fit the model
		this.paper = new joint.dia.Paper({
			el: jQuery("#jjsPaper"),
			width: container[0].clientWidth,
			height: container[0].clientHeight,
			model: graph,
			gridSize: 1,
			padding: 15
		});

		this.makeUMLclassDiagram();

		JJSdiagram.setPaperListener();

		JJSdiagram.setButtonsListener();

		return this.paper;
	},

	//set the paths
	setAssociationPathVertices: function (source, target) {
		if (source === target) {
			var cell = JJSdiagram.paper.model.getCell(source);
			// example: {x: 255, y: 316, width: 181, height: 74, bbox: function, ...
			var cellBBox = JJSdiagram.paper.findViewByModel(cell).getBBox();
			// example: {x: 336, y: 89.5, adhereToRect: function, ...
			var cellCenter = cellBBox.center();

			var point1 = {
				x: cellCenter.x - (cellBBox.width / 2) - 25,
				y: cellCenter.y + 15
			};
			var point3 = {
				x: cellCenter.x - 20,
				y: cellCenter.y + (cellBBox.height / 2) + 25
			};
			var point2 = {
				x: point1.x,
				y: point3.y
			};

			return [point1, point2, point3];
		}
		else {
			return [];
		}
	},

	setPaperListener: function () {

		this.paper.on('cell:pointerclick',
			function (cellView, evt, x, y) {
				Action.selectClass(cellView.model.get('name')[0]);
			}
		);

		this.paper.on('cell:pointerdown',
			function (cellView, evt, x, y) {

				if (JJSdiagram.diagram_type === "UMLclass") {
					var cellPosition = cellView.model.get('position');
					// Make sure the user has clicked on a cellView (and not a transition)
					if (cellPosition !== undefined && cellView.model.get('hasStateMachine')) {
						// Determine if user has clicked on the state-machine icon
						if (x - cellPosition.x < 18 && y - cellPosition.y < 18) {
							// We recycle the JJSdiagram.paper by clearing out the current cells 
							// and re-populating it with the state-machine cells.
							var cellsToRemove = JJSdiagram.paper.model.getCells();
							JJSdiagram.paper.model.removeCells(cellsToRemove);

							JJSdiagram.makeUMLstateDiagram(cellView.model.id);
						}
					}
				}
				else {
					if (cellView.model.id === "back_button") {
						// Recycling the paper
						var cellsToRemove = JJSdiagram.paper.model.getCells();
						JJSdiagram.paper.model.removeCells(cellsToRemove);

						// Resetting the origin change made in makeUMLstateDiagram
						JJSdiagram.paper.setOrigin(0, 0);

						JJSdiagram.makeUMLclassDiagram();
					}
				}
			}
		);

		//The following disconnect and connect event is intended to implement modifying association end, at the moment
		//the workflow is remove then add, please refer to issue #1127 for detail
		//If it is disconnected from a state diagram, then it will remove or modify transition 
		this.paper.off('link:disconnect').on('link:disconnect',
			function(linkView, evt, elmentViewDisconnected, magnet, arrowhead){
				if (JJSdiagram.diagram_type === "UMLclass"){
					if (linkView.model.get('type') === 'uml.Association') {
						if (arrowhead === 'source') {
							JJSdiagram.makeUmpleCodeFromAssociation('removeAssociation',JJSdiagram.paper.model.getCell(elmentViewDisconnected.model.toJSON().id).toJSON(), JJSdiagram.paper.model.getCell(linkView.model.toJSON().target.id).toJSON(), '');
						}
						else if (arrowhead === 'target'){
							JJSdiagram.makeUmpleCodeFromAssociation('removeAssociation',JJSdiagram.paper.model.getCell(linkView.model.toJSON().source.id).toJSON(), JJSdiagram.paper.model.getCell(elmentViewDisconnected.model.toJSON().id).toJSON(),  '');
						}
					}
					else if (linkView.model.get('type') === 'uml.Generalization') {
						if (arrowhead === 'source') {
							JJSdiagram.makeUmpleCodeFromGeneralization('removeGeneralization',JJSdiagram.paper.model.getCell(linkView.model.toJSON().target.id).toJSON(), JJSdiagram.paper.model.getCell(elmentViewDisconnected.model.toJSON().id).toJSON());
						}
						else if (arrowhead === 'target'){
							JJSdiagram.makeUmpleCodeFromGeneralization('removeGeneralization',JJSdiagram.paper.model.getCell(elmentViewDisconnected.model.toJSON().id).toJSON(), JJSdiagram.paper.model.getCell(linkView.model.toJSON().source.id).toJSON());
						}
					}
				}
			}
		);

		this.paper.off('link:connect').on('link:connect',
			function (linkView, evt, elementViewConnected, magnet, arrowhead) {
				if (JJSdiagram.diagram_type === "UMLclass"){
					if (linkView.model.get('type') === 'uml.Association') {
						if(arrowhead === 'source'){
							JJSdiagram.makeUmpleCodeFromAssociation('addJjsAssociation',JJSdiagram.paper.model.getCell(elementViewConnected.model.toJSON().id).toJSON(), JJSdiagram.paper.model.getCell(linkView.model.toJSON().target.id).toJSON());
						} else if(arrowhead === 'target'){
							JJSdiagram.makeUmpleCodeFromAssociation('addJjsAssociation', JJSdiagram.paper.model.getCell(linkView.model.toJSON().source.id).toJSON(), JJSdiagram.paper.model.getCell(elementViewConnected.model.toJSON().id).toJSON());
						}
					}
					
					else if (linkView.model.get('type') === 'uml.Generalization') {
						if (arrowhead === 'source') {
							JJSdiagram.makeUmpleCodeFromGeneralization('addJjsGeneralization',JJSdiagram.paper.model.getCell(linkView.model.toJSON().target.id).toJSON(), JJSdiagram.paper.model.getCell(elementViewConnected.model.toJSON().id).toJSON());
						}
						else if (arrowhead === 'target'){
							JJSdiagram.makeUmpleCodeFromGeneralization('addJjsGeneralization',JJSdiagram.paper.model.getCell(elementViewConnected.model.toJSON().id).toJSON(), JJSdiagram.paper.model.getCell(linkView.model.toJSON().source.id).toJSON());
						}
					} 
				} else if (JJSdiagram.diagram_type === "UMLstate"){
					if (linkView.model.get('type') === 'uml_state_machine.Transition') {
                        if (arrowhead === 'source') {
                            JJSdiagram.makeUmpleCodeFromTransition('addJjsTransition', JJSdiagram.paper.model.getCell(elementViewConnected.model.toJSON().id).toJSON(), JJSdiagram.paper.model.getCell(linkView.model.toJSON().target.id).toJSON(), "event1");
                        } else if (arrowhead === 'target') {
                            JJSdiagram.makeUmpleCodeFromTransition('addJjsTransition', JJSdiagram.paper.model.getCell(linkView.model.toJSON().source.id).toJSON(), JJSdiagram.paper.model.getCell(elementViewConnected.model.toJSON().id).toJSON(), "event1");
                        }
                    }
				}
			}
		);

		//remove listeners for association, transition and generalization
		//Because of the removing on disconnect, the removing button is now only used for full connected association & full connected transition
		this.paper.model.on('remove', _.bind(function (cell) {
			if (cell.isLink()) {
				switch (cell.toJSON().type) {
					case 'uml.Association':
						if (cell.toJSON().source.id !== undefined && cell.toJSON().target.id !== undefined){
							JJSdiagram.makeUmpleCodeFromAssociation('removeAssociation', this.paper.model.getCell(cell.toJSON().source.id).toJSON(), this.paper.model.getCell(cell.toJSON().target.id).toJSON(), cell.toJSON());
						}
						break;
					case 'uml_state_machine.Transition':
						if (cell.toJSON().source.id !== undefined && cell.toJSON().target.id !== undefined){
							JJSdiagram.makeUmpleCodeFromTransition('removeTransition', this.paper.model.getCell(cell.toJSON().source.id).toJSON(), this.paper.model.getCell(cell.toJSON().target.id).toJSON(), cell.toJSON().labels[0].attrs.text.text);
					 }
					 break;
					case 'uml.Generalization':
						if (cell.toJSON().source.id !== undefined && cell.toJSON().target.id !== undefined) {
							JJSdiagram.makeUmpleCodeFromGeneralization('removeGeneralization', this.paper.model.getCell(cell.toJSON().target.id).toJSON(), this.paper.model.getCell(cell.toJSON().source.id).toJSON());
						}
						break;
					default:
						console.log('JJSdiagram -->initJJSDiagram -->graph remove listener--> unknown type');
						//console.log(cell.toJSON().type);
						break;
				}
			}
		}, this));
	},

	setButtonsListener: function () {
		//JointJS UML
		var uml = joint.shapes.uml;

		//customized JointJS UML
		var umpleuml = joint.shapes.umpleuml;

		//click listener for adding class, association, generalization
		jQuery('#buttonAddClass').off('click').on('click.fly', function (e) {
			JJSdiagram.newClassIndex++;
			var x = e.clientX;
			var y = e.clientY;
			var canvas = jQuery("#jjsPaper");

			//remove all other selected item, hidden delete button
			var selectedItem = format("div.palette li.selected");
			jQuery(selectedItem).removeClass("selected");

			jQuery('.html-element button.delete').css('visibility', 'hidden');

			canvas.find("div.html-element").fadeTo(1,1);


			if (Page.useJointJSClassDiagram) {

				var EvenTarget = jQuery(e.target);
				$(EvenTarget).addClass("selected");

				jQuery('body').append('<div id="flyPaper" style="position:fixed;z-index:100;opacity:.5;pointer-events:none;"></div>');
				var flyGraph = new joint.dia.Graph,
					flyPaper = new joint.dia.Paper({
						el: jQuery('#flyPaper'),
						model: flyGraph,
						interactive: false,
						width: 89,
						height: 54
					}),

					//create the class template
					flyShape = new umpleuml.Class({
						position: { x: 20, y: 190 },
						size: { width: 150, height: 80 },
						name: ['NewClass' + JJSdiagram.newClassIndex],
						attributes: [],
						methods: []
					});

				flyShape.position(0, 0);
				flyGraph.addCell(flyShape);
				jQuery("#flyPaper").offset({
					left: e.pageX,
					top: e.pageY
				});
				jQuery('body').on('mousemove.fly', function (e) {
					jQuery("#flyPaper").offset({
						left: e.pageX,
						top: e.pageY
					});
				});
				jQuery('body').on('mouseup.fly', function (e) {
					var x = e.pageX,
						y = e.pageY,
						target = JJSdiagram.paper.$el.offset();
					// Dropped over paper
					if (x > target.left && x < target.left + JJSdiagram.paper.$el.width() && y > target.top && y < target.top + JJSdiagram.paper.$el.height()) {
						var s = flyShape.clone();
						s.position(x - target.left, y - target.top );
						JJSdiagram.paper.model.addCell(s);
						var cell = JJSdiagram.paper.model.getCell(s.id);
						JJSdiagram.makeUmpleCodeFromClass('addNewClass', JJSdiagram.getCurrentObject(JJSdiagram.paper.model.toJSON(), s.id));
					}
					$(EvenTarget).removeClass("selected");
					jQuery('body').off('mousemove.fly').off('mouseup.fly');
					flyShape.remove();
					jQuery('#flyPaper').remove();
				});
			}
		});

		jQuery('#buttonAddAssociation').off('click').on('click.fly', function (e) {
			if (Page.useJointJSClassDiagram) {
				var EvenTarget = jQuery(e.target);
				var clickcount = 0;
				var canvas = jQuery("#jjsPaper");

				if (EvenTarget.hasClass("selected")){
					$(EvenTarget).removeClass("selected");
					clickcount = 0;
					canvas.find("div.html-element").fadeTo("fast",1);
				}else {
					var associationId;
					//remove all other selected editing option as well as hidden delete button
					var selectedItem = format("div.palette li.selected");
					jQuery(selectedItem).removeClass("selected");

					jQuery('.html-element button.delete').css('visibility', 'hidden')

					canvas.find("div.html-element").fadeTo(1,1);

					$(EvenTarget).addClass("selected");
					canvas.find("div.html-element").fadeTo("fast",0.4);
					//paper listener
					JJSdiagram.paper.on('cell:pointerclick', function (cellView, evt, x, y) {
						if(EvenTarget.hasClass("selected")){
							clickcount++;
						}
						if (clickcount === 1) {
							associationId = cellView.model.id;
							cellView.$box.fadeTo("fast",1);
						}
						if (clickcount === 2) {
							cellView.$box.fadeTo("fast",1);
							var link = new uml.Association({
								source: { id: associationId },
								target: { id: cellView.model.id },
								labels: [
									{ position: .05, attrs: { text: { text: '*', 'font-size': '8pt' }, rect: { 'fill-opacity': '0.6' } } },
									{ position: .95, attrs: { text: { text: '*', 'font-size': '8pt' }, rect: { 'fill-opacity': '0.6' } } }
								],
								vertices: JJSdiagram.setAssociationPathVertices(associationId, cellView.model.id),
								umplename: "umpleAssociation_" + JJSdiagram.associationIndex
							});
							link.set('connector', { name: 'jumpover', args: { type: 'gap' } });
							JJSdiagram.paper.model.addCell(link);
							JJSdiagram.makeUmpleCodeFromAssociation('addJjsAssociation', JJSdiagram.getCurrentObject(JJSdiagram.paper.model.toJSON(), associationId), JJSdiagram.getCurrentObject(JJSdiagram.paper.model.toJSON(), cellView.model.id));
							JJSdiagram.paper.off('cell:pointerclick');
							JJSdiagram.setPaperListener();
							$(EvenTarget).removeClass("selected");
							canvas.find("div.html-element").fadeTo("fast",1);
						}
					});
				}
			}
		});

		jQuery('#buttonAddTransition').off('click').on('click.fly', function (e) {
			if (Page.useJointJSClassDiagram) {
				var EvenTarget = jQuery(e.target);
				var clickcount = 0;
				var canvas = jQuery("#jjsPaper");

				if (EvenTarget.hasClass("selected")){
					$(EvenTarget).removeClass("selected");
					clickcount = 0;
					canvas.find("div.html-element").fadeTo("fast",1);
				}else {
					var transitionId;
					//remove all other selected editing option as well as hidden delete button
					var selectedItem = format("div.palette li.selected");
					jQuery(selectedItem).removeClass("selected");

					jQuery('.html-element button.delete').css('visibility', 'hidden')

					canvas.find("div.html-element").fadeTo(1,1);

					$(EvenTarget).addClass("selected");
					canvas.find("div.html-element").fadeTo("fast",0.4);
					//paper listener
					JJSdiagram.paper.on('cell:pointerclick', function (cellView, evt, x, y) {
						if(EvenTarget.hasClass("selected")){
							clickcount++;
						}
						if (clickcount === 1) {
							transitionId = cellView.model.id;
							cellView.$box.fadeTo("fast",1);
						}
						if (clickcount === 2) {
							cellView.$box.fadeTo("fast",1);
							// Create the transistion from pseudo-start to starting state.
							//add labels
							var link = new joint.shapes.uml_state_machine.Transition({
								source: { id: transitionId },
								target: { id: cellView.model.id },
								attrs: { '.connection-wrap': { fill: 'none' }, '.connection': { fill: 'none' }},
                            	labels: [{ position: .5, attrs: { text: { text: 'event1', 'font-size': '8pt' } } }],
                            	umplename: "umpleTransition_" + JJSdiagram.transitionIndex
							});
							
							link.set('connector', { name: 'jumpover', args: { type: 'gap' } });
							JJSdiagram.paper.model.addCell(link);
							JJSdiagram.makeUmpleCodeFromTransition('addJjsTransition', JJSdiagram.getCurrentObject(JJSdiagram.paper.model.toJSON(), transitionId), JJSdiagram.getCurrentObject(JJSdiagram.paper.model.toJSON(), cellView.model.id), "event1");
							JJSdiagram.paper.off('cell:pointerclick');
							JJSdiagram.setPaperListener();
							$(EvenTarget).removeClass("selected");
							canvas.find("div.html-element").fadeTo("fast",1);
						}
					});
				}
			}
		});
		jQuery('#buttonAddGeneralization').off('click').on('click.fly', function (e) {
			if (Page.useJointJSClassDiagram) {
				var EvenTarget = jQuery(e.target);
				var clickcount = 0;
				var canvas = jQuery("#jjsPaper");

				if (EvenTarget.hasClass("selected")){
					$(EvenTarget).removeClass("selected");
					clickcount = 0;
					canvas.find("div.html-element").fadeTo("fast",1);
				}else {
					var associationId;
					//remove all other selected editing option as well as hidden delete button
					var selectedItem = format("div.palette li.selected");
					jQuery(selectedItem).removeClass("selected");

					jQuery('.html-element button.delete').css('visibility', 'hidden');

					canvas.find("div.html-element").fadeTo(1,1);

					//fade the class boxes
					$(EvenTarget).addClass("selected");
					canvas.find("div.html-element").fadeTo("fast",0.4);
					//paper listener
					JJSdiagram.paper.on('cell:pointerclick', function (cellView, evt, x, y) {
						if(EvenTarget.hasClass("selected")){
							clickcount++;
						}
						if (clickcount === 1) {
							associationId = cellView.model.id;
							cellView.$box.fadeTo("fast",1);
						}
						if (clickcount === 2) {
							cellView.$box.fadeTo("fast",1);
							var link = new uml.Generalization({ source: { id: cellView.model.id }, target: { id: associationId } });
							link.set('connector', { name: 'jumpover', args: { type: 'gap' } });
							JJSdiagram.paper.model.addCell(link);
							JJSdiagram.makeUmpleCodeFromGeneralization('addJjsGeneralization', JJSdiagram.getCurrentObject(JJSdiagram.paper.model.toJSON(), associationId), JJSdiagram.getCurrentObject(JJSdiagram.paper.model.toJSON(), cellView.model.id));
							JJSdiagram.paper.off('cell:pointerclick');
							JJSdiagram.setPaperListener();
							$(EvenTarget).removeClass("selected");
							canvas.find("div.html-element").fadeTo("fast",1);
						}
					});
				}
			}
		});

		jQuery('#buttonDeleteEntity').off('click').on('click.fly', function (e) {
			var EvenTarget = jQuery(e.target);

			var canvas = jQuery("#jjsPaper");
			canvas.find("div.html-element").fadeTo(1,1);

			if (EvenTarget.hasClass("selected")) {
				$(EvenTarget).removeClass("selected");
				jQuery('.html-element button.delete').css('visibility', 'hidden');
			}else {
				var selectedItem = format("div.palette li.selected");
				jQuery(selectedItem).removeClass("selected");
				$(EvenTarget).addClass("selected");
				jQuery('.html-element button.delete').css('visibility', 'visible');

			}
		});

		jQuery('#buttonPhotoReady').change(function () {
			if (this.checked){
				jQuery('.html-element button.delete').css('visibility', 'hidden');
				jQuery('img#classIcon').css('visibility', 'hidden');
				jQuery('img.edit').css('visibility', 'hidden');
				jQuery('img.deleteAttr').css('visibility', 'hidden');
				jQuery('img.deleteMet').css('visibility', 'hidden');
				jQuery('.attributInput input').prop('placeholder', '');
				jQuery('.methodInput input').prop('placeholder', '');
			}else{
				jQuery('img#classIcon').css('visibility', 'visible');
				jQuery('img.edit').css('visibility', 'visible');
				jQuery('img.deleteAttr').css('visibility', 'visible');
				jQuery('img.deleteMet').css('visibility', 'visible');
				jQuery('.attributInput input').prop('placeholder', 'Add More');
				jQuery('.methodInput input').prop('placeholder', 'Add More');
			}
		});


	},

	//return the object in JJS JSON that has certain id
	getCurrentObject: function (jjsJson, selectedID) {
		for (var i = 0; i < jjsJson.cells.length; i++) {
			if (jjsJson.cells[i]['id'] == selectedID) return jjsJson.cells[i];
		}
	},

	//convert the diagram JSON to umple code
	makeUmpleCodeFromClass: function (actionType, jjsJson) {
		var actionCode;
		var tempAttr;
		var tempMed;
		var codeChange = true;
		jjsJson.position.height = jjsJson.size.height;
		jjsJson.position.width = jjsJson.size.width;
		var actionCodeObj = {
			"displayColor": "transparent",
			"position": jjsJson.position,
			"attributes": [

			],
			"methods": [

			],
			"interfaces": [

			],
			"id": jjsJson.name[0],
			"name": jjsJson.name[0],
			//"isInterface": "false",
			//"isAbstract": "false"
		};
		switch (actionType) {
			case 'addNewClass':
				actionCode = "action=addClass&actionCode=";
				actionCode += JSON.stringify(actionCodeObj);
				break;
			case 'editClassName':
				actionCode = "action=editClass&actionCode=";
				var attModifier = null;
				for (var k = 0; k < jjsJson.attributes.length; k++) {
					tempAttr = jjsJson.attributes[k].split(":").map(function (item) {
						return item.trim();
					});

					//split modifier
					if (tempAttr[0].charAt(0) === '-' || tempAttr[0].charAt(0) === '#' || tempAttr[0].charAt(0) === '~' || tempAttr[0].charAt(0) === '+') {
						switch (tempAttr[0].charAt(0)) {
							case "+":
								attModifier = "public";
								break;
							case "~":
								attModifier = "package";
								break;
							case "#":
								attModifier = "protected";
								break;
							case "-":
								attModifier = "private";
								break;
							default:
								console.log('Invalid modifier in jjs_parser.js -->makeUmpleCodeFromClass');
						}
						tempAttr[0] = tempAttr[0].slice(1);
						tempAttr[0] = tempAttr[0].trim();
					}

					//add unchanged attributes
					actionCodeObj.attributes.push({
						"type": tempAttr[1],
						"name": tempAttr[0],
						"textColor": "black",
						"aColor": "black",
						'modifier': attModifier
					});
				}
				actionCodeObj.oldname = arguments[2];
				actionCode += JSON.stringify(actionCodeObj);
				break;
			case 'addAttribute':
				var attributeIndex = arguments[2];
				var attModifier = null;
				for (var k = 0; k < jjsJson.attributes.length; k++) {
					tempAttr = jjsJson.attributes[k].split(":").map(function (item) {
						return item.trim();
					});

					//split modifier
					if (tempAttr[0].charAt(0) === '-' || tempAttr[0].charAt(0) === '#' || tempAttr[0].charAt(0) === '~' || tempAttr[0].charAt(0) === '+') {
						switch (tempAttr[0].charAt(0)) {
							case "+":
								attModifier = "public";
								break;
							case "~":
								attModifier = "package";
								break;
							case "#":
								attModifier = "protected";
								break;
							case "-":
								attModifier = "private";
								break;
							default:
								console.log('Invalid modifier in jjs_parser.js -->makeUmpleCodeFromClass');
						}
						tempAttr[0] = tempAttr[0].slice(1);
						tempAttr[0] = tempAttr[0].trim();
					}

					//attribute to add
					if (k === attributeIndex) {
						actionCodeObj.attributes.push({
							"type": tempAttr[1],
							"name": tempAttr[0],
							"textColor": "black",
							"aColor": "black",
							"newType": tempAttr[1],
							"newName": tempAttr[0],
							'modifier': attModifier
						});
					}
					//other unchanged attibutes
					else {
						actionCodeObj.attributes.push({
							"type": tempAttr[1],
							"name": tempAttr[0],
							"textColor": "black",
							"aColor": "black",
							'modifier': attModifier
						});
					}
				}
				actionCode = "action=editClass&actionCode=";
				actionCode += JSON.stringify(actionCodeObj);
				break;
			case 'removeAttribute':
				var oldAttribute = arguments[2];
				var attModifier = null;
				for (var k = 0; k < jjsJson.attributes.length; k++) {
					tempAttr = jjsJson.attributes[k].split(":").map(function (item) {
						return item.trim();
					});

					//split modifier
					if (tempAttr[0].charAt(0) === '-' || tempAttr[0].charAt(0) === '#' || tempAttr[0].charAt(0) === '~' || tempAttr[0].charAt(0) === '+') {
						switch (tempAttr[0].charAt(0)) {
							case "+":
								attModifier = "public";
								break;
							case "~":
								attModifier = "package";
								break;
							case "#":
								attModifier = "protected";
								break;
							case "-":
								attModifier = "private";
								break;
							default:
								console.log('Invalid modifier in jjs_parser.js -->makeUmpleCodeFromClass');
						}
						tempAttr[0] = tempAttr[0].slice(1);
						tempAttr[0] = tempAttr[0].trim();
					}

					//attribute to add
					actionCodeObj.attributes.push({
						"type": tempAttr[1],
						"name": tempAttr[0],
						"textColor": "black",
						"aColor": "black",
						'modifier': attModifier
					});
				}
				//add deleted attribute
				tempAttr = oldAttribute.split(":").map(function (item) {
					return item.trim();
				});
				actionCodeObj.attributes.push({
					"type": tempAttr[1],
					"name": tempAttr[0],
					"textColor": "black",
					"aColor": "black",
					"deleteType": tempAttr[1],
					"deleteName": tempAttr[0],
					'modifier': attModifier
				});

				actionCode = "action=editClass&actionCode=";
				actionCode += JSON.stringify(actionCodeObj);
				break;
			case 'editAttribute':
				var oldName = arguments[3];
				var modifyIndex = arguments[2];
				for (var k = 0; k < jjsJson.attributes.length; k++) {
					tempAttr = jjsJson.attributes[k].split(":").map(function (item) {
						return item.trim();
					});

					//split modifier
					if (tempAttr[0].charAt(0) === '-' || tempAttr[0].charAt(0) === '#' || tempAttr[0].charAt(0) === '~' || tempAttr[0].charAt(0) === '+') {
						switch (tempAttr[0].charAt(0)) {
							case "+":
								attModifier = "public";
								break;
							case "~":
								attModifier = "package";
								break;
							case "#":
								attModifier = "protected";
								break;
							case "-":
								attModifier = "private";
								break;
							default:
								console.log('Invalid modifier in jjs_parser.js -->makeUmpleCodeFromClass');
						}
						tempAttr[0] = tempAttr[0].slice(1);
						tempAttr[0] = tempAttr[0].trim();
					}

					//attribute to modify
					if (k === modifyIndex) {
						actionCodeObj.attributes.push({
							"type": tempAttr[1],
							"name": tempAttr[0],
							"textColor": "black",
							"aColor": "black",
							"oldName": oldName,
							'modifier': attModifier
						});
					}
					//other unchanged attibutes
					else {
						actionCodeObj.attributes.push({
							"type": tempAttr[1],
							"name": tempAttr[0],
							"textColor": "black",
							"aColor": "black",
							'modifier': attModifier
						});
					}
				}
				actionCode = "action=editClass&actionCode=";
				actionCode += JSON.stringify(actionCodeObj);
				break;
			case 'removeClass':
				actionCode = "action=removeClass&actionCode=";
				actionCode += JSON.stringify(actionCodeObj);
				break;
			case 'moveClass':
				actionCode = "action=editClass&actionCode=";
				var attModifier = null;

				//attributes
				for (var k = 0; k < jjsJson.attributes.length; k++) {
					tempAttr = jjsJson.attributes[k].split(":").map(function (item) {
						return item.trim();
					});

					//split modifier
					if (tempAttr[0].charAt(0) === '-' || tempAttr[0].charAt(0) === '#' || tempAttr[0].charAt(0) === '~' || tempAttr[0].charAt(0) === '+') {
						switch (tempAttr[0].charAt(0)) {
							case "+":
								attModifier = "public";
								break;
							case "~":
								attModifier = "package";
								break;
							case "#":
								attModifier = "protected";
								break;
							case "-":
								attModifier = "private";
								break;
							default:
								console.log('Invalid modifier in jjs_parser.js -->makeUmpleCodeFromClass');
						}
						tempAttr[0] = tempAttr[0].slice(1);
						tempAttr[0] = tempAttr[0].trim();
					}

					//add unchanged attributes
					actionCodeObj.attributes.push({
						"type": tempAttr[1],
						"name": tempAttr[0],
						"textColor": "black",
						"aColor": "black",
						'modifier': attModifier
					});
				}
				actionCode += JSON.stringify(actionCodeObj);
				codeChange = false;
				break;
			case 'addMethod':
				var methodIndex = arguments[2];
				var methodVisibility = null;
				//attributes
				var attModifier = null;
				for (var k = 0; k < jjsJson.attributes.length; k++) {
					tempAttr = jjsJson.attributes[k].split(":").map(function (item) {
						return item.trim();
					});

					//split modifier
					if (tempAttr[0].charAt(0) === '-' || tempAttr[0].charAt(0) === '#' || tempAttr[0].charAt(0) === '~' || tempAttr[0].charAt(0) === '+') {
						switch (tempAttr[0].charAt(0)) {
							case "+":
								attModifier = "public";
								break;
							case "~":
								attModifier = "package";
								break;
							case "#":
								attModifier = "protected";
								break;
							case "-":
								attModifier = "private";
								break;
							default:
								console.log('Invalid modifier in jjs_parser.js -->makeUmpleCodeFromClass');
						}
						tempAttr[0] = tempAttr[0].slice(1);
						tempAttr[0] = tempAttr[0].trim();
					}

					//add unchanged attributes
					actionCodeObj.attributes.push({
						"type": tempAttr[1],
						"name": tempAttr[0],
						"textColor": "black",
						"aColor": "black",
						'modifier': attModifier
					});
				}

				//methods
				for (var k = 0; k < jjsJson.methods.length; k++) {
					var tempParameters = null;
					tempMed = jjsJson.methods[k].split(":").map(function (item) {
						return item.trim();
					});

					/**
					 * default type
					 */
					if (!(tempMed[0].charAt(0) === '-' || tempMed[0].charAt(0) === '#' || tempMed[0].charAt(0) === '~' || tempMed[0].charAt(0) === '+')) {
						tempMed[0] = '- ' + tempMed[0];
					}
					//split visibility
					if (tempMed[0].charAt(0) === '-' || tempMed[0].charAt(0) === '#' || tempMed[0].charAt(0) === '~' || tempMed[0].charAt(0) === '+') {
						switch (tempMed[0].charAt(0)) {
							case "+":
								methodVisibility = "public";
								break;
							case "~":
								methodVisibility = "package";
								break;
							case "#":
								methodVisibility = "protected";
								break;
							case "-":
								methodVisibility = "private";
								break;
							default:
								console.log('Invalid modifier in jjs_parser.js -->makeUmpleCodeFromClass');
						}
						tempMed[0] = tempMed[0].slice(1);
						tempMed[0] = tempMed[0].trim();
						if (tempMed[0].slice(-2) === '()') {
							tempMed[0] = tempMed[0].slice(0, -2);
						}
						else {
							tempParameters = tempMed[0].split("(").map(function (item) {
								return item.trim();
							});
							tempMed[0] = tempParameters[0];
							tempParameters[1] = tempParameters[1].slice(0, -1);
							tempParameters[1] = tempParameters[1].split(",").map(function (item) {
								return item.trim();
							});
							for (var ii = 0; ii < tempParameters[1].length; ii++) {
								var paraArray = tempParameters[1][ii].split('');
								tempParameters[1][ii] = {};
								for (kk = 0; kk < paraArray.length; kk++) {
									tempParameters[1][ii][kk] = paraArray[kk];
								}
							}
						}
					}

					//Add Methods
					if (k === methodIndex) {
						actionCodeObj.methods.push({
							"visibility": methodVisibility,
							"isAbstract": '',
							"type": tempMed[1],
							"name": tempMed[0],
							"parameters": tempParameters ? tempParameters[1] : [{}],
							"newVisibility": methodVisibility,
							"newType": tempMed[1],
							"newName": tempMed[0],
							"newParameters": tempParameters ? tempParameters[1] : [{}]
						});
					}
					else {
						actionCodeObj.methods.push({
							"visibility": methodVisibility,
							"isAbstract": '',
							"type": tempMed[1],
							"name": tempMed[0],
							"parameters": tempParameters ? tempParameters[1] : [{}]
						});
					}

				}

				actionCode = "action=editClass&actionCode=";
				actionCode += JSON.stringify(actionCodeObj);
				break;
			case 'removeMethod':
				var oldMethod = arguments[2];
				var attModifier = null;
				//attributes
				for (var k = 0; k < jjsJson.attributes.length; k++) {
					tempAttr = jjsJson.attributes[k].split(":").map(function (item) {
						return item.trim();
					});

					//split modifier
					if (tempAttr[0].charAt(0) === '-' || tempAttr[0].charAt(0) === '#' || tempAttr[0].charAt(0) === '~' || tempAttr[0].charAt(0) === '+') {
						switch (tempAttr[0].charAt(0)) {
							case "+":
								attModifier = "public";
								break;
							case "~":
								attModifier = "package";
								break;
							case "#":
								attModifier = "protected";
								break;
							case "-":
								attModifier = "private";
								break;
							default:
								console.log('Invalid modifier in jjs_parser.js -->makeUmpleCodeFromClass');
						}
						tempAttr[0] = tempAttr[0].slice(1);
						tempAttr[0] = tempAttr[0].trim();
					}

					//attribute to add
					actionCodeObj.attributes.push({
						"type": tempAttr[1],
						"name": tempAttr[0],
						"textColor": "black",
						"aColor": "black",
						'modifier': attModifier
					});
				}

				//methods
				for (var k = 0; k < jjsJson.methods.length; k++) {
					var tempParameters = null;

					tempMed = jjsJson.methods[k].split(":").map(function (item) {
						return item.trim();
					});

					/**
					 * default type
					 */
					if (!(tempMed[0].charAt(0) === '-' || tempMed[0].charAt(0) === '#' || tempMed[0].charAt(0) === '~' || tempMed[0].charAt(0) === '+')) {
						tempMed[0] = '- ' + tempMed[0];
					}

					//split visibility
					if (tempMed[0].charAt(0) === '-' || tempMed[0].charAt(0) === '#' || tempMed[0].charAt(0) === '~' || tempMed[0].charAt(0) === '+') {
						switch (tempMed[0].charAt(0)) {
							case "+":
								methodVisibility = "public";
								break;
							case "~":
								methodVisibility = "package";
								break;
							case "#":
								methodVisibility = "protected";
								break;
							case "-":
								methodVisibility = "private";
								break;
							default:
								console.log('Invalid modifier in jjs_parser.js -->makeUmpleCodeFromClass');
						}
						tempMed[0] = tempMed[0].slice(1);
						tempMed[0] = tempMed[0].trim();
						if (tempMed[0].slice(-2) === '()') {
							tempMed[0] = tempMed[0].slice(0, -2);
						}
						else {
							tempParameters = tempMed[0].split("(").map(function (item) {
								return item.trim();
							});
							tempMed[0] = tempParameters[0];
							tempParameters[1] = tempParameters[1].slice(0, -1);
							tempParameters[1] = tempParameters[1].split(",").map(function (item) {
								return item.trim();
							});
							for (var ii = 0; ii < tempParameters[1].length; ii++) {
								var paraArray = tempParameters[1][ii].split('');
								tempParameters[1][ii] = {};
								for (kk = 0; kk < paraArray.length; kk++) {
									tempParameters[1][ii][kk] = paraArray[kk];
								}
							}
						}
					}


					//Add other methods
					actionCodeObj.methods.push({
						"visibility": methodVisibility,
						"isAbstract": '',
						"type": tempMed[1],
						"name": tempMed[0],
						"parameters": tempParameters ? tempParameters[1] : [{}]
					});

				}
				var oldMethodType;
				var oldMethodVisibility;
				var oldMethodName;
				var tempParameters = null;
				tempMed = oldMethod.split(":").map(function (item) {
					return item.trim();
				});

				if (!(tempMed[0].charAt(0) === '-' || tempMed[0].charAt(0) === '#' || tempMed[0].charAt(0) === '~' || tempMed[0].charAt(0) === '+')) {
					tempMed[0] = '- ' + tempMed[0];
				}

				if (tempMed[0].charAt(0) === '-' || tempMed[0].charAt(0) === '#' || tempMed[0].charAt(0) === '~' || tempMed[0].charAt(0) === '+') {
					switch (tempMed[0].charAt(0)) {
						case "+":
							oldMethodVisibility = "public";
							break;
						case "~":
							oldMethodVisibility = "package";
							break;
						case "#":
							oldMethodVisibility = "protected";
							break;
						case "-":
							oldMethodVisibility = "private";
							break;
						default:
							console.log('Invalid modifier in jjs_parser.js -->makeUmpleCodeFromClass');
					}
					tempMed[0] = tempMed[0].slice(1);
					tempMed[0] = tempMed[0].trim();
					if (tempMed[0].slice(-2) === '()') {
						tempMed[0] = tempMed[0].slice(0, -2);
					}
					else {
						tempParameters = tempMed[0].split("(").map(function (item) {
							return item.trim();
						});
						tempMed[0] = tempParameters[0];
						tempParameters[1] = tempParameters[1].slice(0, -1);
						tempParameters[1] = tempParameters[1].split(",").map(function (item) {
							return item.trim();
						});
						for (var ii = 0; ii < tempParameters[1].length; ii++) {
							var paraArray = tempParameters[1][ii].split('');
							tempParameters[1][ii] = {};
							for (kk = 0; kk < paraArray.length; kk++) {
								tempParameters[1][ii][kk] = paraArray[kk];
							}
						}
					}
				}

				actionCodeObj.methods.push({
					"visibility": oldMethodVisibility,
					"isAbstract": '',
					"type": tempMed[1],
					"name": tempMed[0],
					"parameters": tempParameters ? tempParameters[1] : [{}],
					"deleteVisibility": oldMethodVisibility,
					"deleteType": tempMed[1],
					"deleteName": tempMed[0],
					"deleteParameters": tempParameters ? tempParameters[1] : [{}]
				});

				actionCode = "action=editClass&actionCode=";
				actionCode += JSON.stringify(actionCodeObj);
				break;
			default:
				console.log("Invalid action type in function 'makeUmpleCodeFromClass'");
		}
		DiagramEdit.updateUmpleText({
			actionCode: actionCode,
			codeChange: codeChange
		});
	},

	//convert the diagram JSON to umple code
	makeUmpleCodeFromAssociation: function (actionType, jjsJsonOne, jjsJsonTwo) {
		jjsJsonOne.position.height = jjsJsonOne.size.height;
		jjsJsonOne.position.width = jjsJsonOne.size.width;
		jjsJsonTwo.position.height = jjsJsonTwo.size.height;
		jjsJsonTwo.position.width = jjsJsonTwo.size.width;
		var nameOne = "";
		var nameTwo = "";

		if (jjsJsonOne.name[0] <= jjsJsonTwo.name[0]){
			nameOne = jjsJsonOne.name[0];
			nameTwo = jjsJsonTwo.name[0];
		}else {
			nameOne = jjsJsonTwo.name[0];
			nameTwo = jjsJsonOne.name[0];
		}
		var actionCodeObj = {
			"classOnePosition": jjsJsonOne.position,
			"classTwoPosition": jjsJsonTwo.position,
			"offsetOnePosition": {
				"x": "0",
				"y": "0",
				"width": "0",
				"height": "0"
			},
			"offsetTwoPosition": {
				"x": "0",
				"y": "0",
				"width": "0",
				"height": "0"
			},
			"multiplicityOne": "*",
			"multiplicityTwo": "*",
			"name": nameOne + "__" + nameTwo,
			"roleOne": "",
			"roleTwo": "",
			"isSymmetricReflexive": "false",
			"isLeftNavigable": "true",
			"isRightNavigable": "true",
			"isLeftComposition": "false",
			"isRightComposition": "false",
			"color": "black",
			"id": "umpleAssociation_" + this.associationIndex,
			"classOneId": jjsJsonOne.name[0],
			"classTwoId": jjsJsonTwo.name[0]
		};

		switch (actionType) {
			case 'addJjsAssociation':
				actionCode = "action=addAssociation&actionCode=";
				actionCode += JSON.stringify(actionCodeObj);
				this.associationIndex++;
				break;
			case 'removeAssociation':
				actionCode = "action=removeAssociation&actionCode=";
				if (arguments[3]) {
					actionCodeObj.id = arguments[3].umplename;
				}
				else {
					console.log('JJSdiagram.makeUmpleCodeFromAssociation: removeAssociation --> missing arguments[3].');
				}
				actionCode += JSON.stringify(actionCodeObj);
				break;
			default:
				console.log('JJSdiagram.makeUmpleCodeFromAssociation: action type error.');
				break;
		}
		
		DiagramEdit.updateUmpleText({
			actionCode: actionCode,
			codeChange: true
		});
	},

	//convert the diagram JSON to umple code
	makeUmpleCodeFromTransition: function (actionType, jjsJsonOne, jjsJsonTwo, event) {
        var nameOne = "";
        var nameTwo = "";
		var actionCodeObj = {
			"fromStateId": jjsJsonOne.name,
			"toStateId": jjsJsonTwo.name,
			"event": event,
            "id": "umpleTransition_" + this.transitionIndex,
			"name": jjsJsonOne.name + "_" + event + "_" + jjsJsonTwo.name
   		 };

		switch (actionType) {
			case 'addJjsTransition':
				actionCode = "action=addTransition&actionCode=";
				actionCode += JSON.stringify(actionCodeObj);
				this.transitionIndex++;
				break;
			case 'removeTransition':
				actionCode = "action=removeTransition&actionCode=";
				actionCode += JSON.stringify(actionCodeObj);
				break;
			default:
                console.log('JJSdiagram.makeUmpleCodeFromTransition: action type error.');
				break;
		}
		
		DiagramEdit.updateUmpleText({
			actionCode: actionCode,
			codeChange: true
		});
	},
	//convert the diagram JSON to umple code
	makeUmpleCodeFromGeneralization: function (actionType, parent, child) {
		parent.position.height = parent.size.height;
		parent.position.width = parent.size.width;
		child.position.height = child.size.height;
		child.position.width = child.size.width;

		var actionCodeObj = {
			"parentId": parent.name[0],
			"childId": child.name[0],
			"parentPosition": parent.position,
			"childPosition": child.position
		};
		switch (actionType) {
			case 'addJjsGeneralization':
				actionCode = "action=addGeneralization&actionCode=";
				actionCode += JSON.stringify(actionCodeObj);
				break;
			case 'removeGeneralization':
				delete actionCodeObj.parentId;
				delete actionCodeObj.parentPosition;
				delete actionCodeObj.childPosition;
				actionCode = "action=removeGeneralization&actionCode=";
				actionCode += JSON.stringify(actionCodeObj);
				break;
			default:
				break;
		}


		DiagramEdit.updateUmpleText({
			actionCode: actionCode,
			codeChange: true
		});
	},

	makeUMLclassDiagram: function () {

		this.diagram_type = "UMLclass";

		JJSdiagram.paper.model.addCells(this.JJsParse.makeClasses(this.JSONmodel));
		JJSdiagram.paper.model.addCells(this.JJsParse.makeAssociations(this.JSONmodel));
		JJSdiagram.paper.model.addCells(this.JJsParse.makeGeneralizationLinks(this.JSONmodel));
		JJSdiagram.paper.model.addCells(this.JJsParse.makeInterfaceLinks(this.JSONmodel));

		var bbox = JJSdiagram.paper.model.getBBox(JJSdiagram.paper.model.getElements());
		// This occurs when no model has been loaded.
		if (bbox !== null) {
			// Resize the paper to fit all of the cells.
			this.paper.setDimensions(Math.max(bbox.width + 100, this.container.width()), Math.max(bbox.height + 100, this.container.height()));
		}

		// Because the auto-layout squeezes the diagram towards the left-hand side.
		JJSdiagram.paper.setOrigin(100, 50);

		// Sort out the overlapping associations
		JJSdiagram.paper.model.getCells().forEach(function (cell) {
			JJSdiagram.JJsUtils.adjustVertices(JJSdiagram.paper.model, cell);
		});
	},

	makeUMLstateDiagram: function (UMLclassName) {
		this.diagram_type = "UMLstate";

		var class_sms = _.find(JJSdiagram.JSONmodel.umpleClasses, { "name": UMLclassName }).stateMachines;

		// Only set up for a single top-level state machine
		var graph_cells = JJSdiagram.JJsParse.parseStateMachine(class_sms[0]);

		// Make the back button and add it to the graph
		var back = new joint.shapes.uml_state_machine.BackButton({
			position: { x: 0, y: 0 },
			size: { width: 100, height: 40 },
			id: "back_button"
		});
		JJSdiagram.paper.model.addCell(back);

		// Auto-layout the model.
		joint.layout.DirectedGraph.layout(JJSdiagram.paper.model, { setLinkVertices: true, rankDir: "TB", nodeSep: 50, edgeSep: 80 });

		// Add the last states and transitions to the graph, after making the list FILO.
		//JJSdiagram.JJsParse.notYetAddedStatesStack.reverse()
		JJSdiagram.JJsParse.notYetAddedStatesStack.forEach(function (cell) {
			cell.doEmbed(JJSdiagram.paper);
			cell.updateRectangles(JJSdiagram.paper);
			JJSdiagram.paper.model.addCell(cell);
		});

		//JJSdiagram.paper.model.addCells(JJSdiagram.JJsParse.notYetAddedStatesStack);

		JJSdiagram.paper.model.addCells(JJSdiagram.JJsParse.notYetAddedTransitionsStack);

		// Reset the stacks, or see these entities show up in other diagrams.
		JJSdiagram.JJsParse.notYetAddedStatesStack = [];
		JJSdiagram.JJsParse.notYetAddedTransitionsStack = [];

		// Sort out overlapping transitions
		JJSdiagram.paper.model.getCells().forEach(function (cell) {
			JJSdiagram.JJsUtils.adjustVertices(JJSdiagram.paper.model, cell);
		});



		var bbox = JJSdiagram.paper.model.getBBox(JJSdiagram.paper.model.getElements());
		// This occurs when no model has been loaded.
		if (bbox !== null) {
			// Resize the paper to fit all of the cells, plus some breathing room.
			this.paper.setDimensions(Math.max(bbox.width + 200, this.container.width()), Math.max(bbox.height + 150, this.container.height()));
		}
		// Because the auto-layout squeezes the diagram towards the left-hand side.
		JJSdiagram.paper.setOrigin(100, 70);
	},

	JJsUtils: {

		// from JointJS tutorial: http://resources.jointjs.com/tutorial/multiple-links-between-elements
		adjustVertices: function (graph, cell) {

			// If the cell is a view, find its model.
			cell = cell.model || cell;

			if (cell instanceof joint.dia.Element) {

				_.chain(graph.getConnectedLinks(cell)).groupBy(function (link) {
					// the key of the group is the model id of the link's source or target, but not our cell id.
					return _.omit([link.get('source').id, link.get('target').id], cell.id)[0];
				}).each(function (group, key) {
					// If the member of the group has both source and target model adjust vertices.
					if (key !== 'undefined') adjustVertices(graph, _.first(group));
				});

				return;
			}

			// The cell is a link. Let's find its source and target models.
			var srcId = cell.get('source').id || cell.previous('source').id;
			var trgId = cell.get('target').id || cell.previous('target').id;

			// If one of the ends is not a model, the link has no siblings.
			if (!srcId || !trgId) return;

			var siblings = _.filter(graph.getLinks(), function (sibling) {

				var _srcId = sibling.get('source').id;
				var _trgId = sibling.get('target').id;

				return (_srcId === srcId && _trgId === trgId) || (_srcId === trgId && _trgId === srcId);
			});

			switch (siblings.length) {

				case 0:
					// The link was removed and had no siblings.
					break;

				case 1:
					// There is only one link between the source and target. No vertices needed.

					// Have commented this out, as the positive benefits are unknown,
					//   but it certainly breaks the work established to make generalization links and self-referential associations
					// cell.unset('vertices');
					break;

				default:

					// There is more than one siblings. We need to create vertices.

					// First of all we'll find the middle point of the link.
					var srcCenter = graph.getCell(srcId).getBBox().center();
					var trgCenter = graph.getCell(trgId).getBBox().center();
					var midPoint = g.line(srcCenter, trgCenter).midpoint();

					// Then find the angle it forms.
					var theta = srcCenter.theta(trgCenter);

					// This is the maximum distance between links
					var gap = 20;

					_.each(siblings, function (sibling, index) {

						// We want the offset values to be calculated as follows 0, 20, 20, 40, 40, 60, 60 ..
						var offset = gap * Math.ceil(index / 2);

						// Now we need the vertices to be placed at points which are 'offset' pixels distant
						// from the first link and forms a perpendicular angle to it. And as index goes up
						// alternate left and right.
						//
						//  ^  odd indexes 
						//  |
						//  |---->  index 0 line (straight line between a source center and a target center.
						//  |
						//  v  even indexes
						var sign = index % 2 ? 1 : -1;
						var angle = g.toRad(theta + sign * 90);

						// We found the vertex.
						var vertex = g.point.fromPolar(offset, angle, midPoint);

						sibling.set('vertices', [{ x: vertex.x, y: vertex.y }]);
					});
			}
		}
	},

	JJsParse: {

		addAttributes: function (attrs) {
			// { "type" : "String", "name" : "startTime", "modifier" : "", "traceColor" : "black" }
			var attributes = new Array();

			var parseAttributes = function (attribute) {
				var text = JJSdiagram.JJsParse.makeModifier(attribute.modifier) + " " + attribute.name + ": " + attribute.type;
				attributes.push(text);
			};

			attrs.forEach(parseAttributes);

			// If there are no attributes, then reveal their absence
			// why ?
			// if (attributes.length == 0 && Page.showAttributes) {
			// 	attributes.push(" ");
			// }

			return attributes;
		},

		addMethods: function (meths) {
			// {type: "void", name: "setDoorTimer", parameters: "Integer", visibility: "public", isAbstract: "false"}
			var methods = new Array();

			var parseMethods = function (method) {
				var text = JJSdiagram.JJsParse.makeModifier(method.visibility) + " " + method.name + "(" + method.parameters + ") : " + method.type;
				methods.push(text);
			};

			meths.forEach(parseMethods);

			// If there are no methods, then reveal their absence
			// why?
			/*
			if (methods.length == 0 && Page.showMethods) {
				methods.push(" ");
			}
			*/

			return methods;
		},

		makeModifier: function (modifier) {
			var symbol = "";
			switch (modifier) {
				case "public":
					symbol = "+";
					break;
				case "package":
					symbol = "~";
					break;
				case "protected":
					symbol = "#";
					break;
				case "private":
					symbol = "-";
					break;
				default:
					symbol = "-";
			}
			return symbol;
		},

		updateRectangles: function (UMLclass) {

			var attrs = UMLclass.get('attrs');

			var rects = [
				{ type: 'name', text: UMLclass.getClassName() },
				{ type: 'attrs', text: UMLclass.get('attributes') },
				{ type: 'methods', text: UMLclass.get('methods') }
			];

			var offsetY = 0, maxWidth = UMLclass.attributes.size.width;

			_.each(rects, function (rect) {

				var lines = _.isArray(rect.text) ? rect.text : [rect.text];
				var rectHeight = lines[0] != null ? lines.length * 9 + 10 : 0;

				// Calculate the longest attribute or method string
				lines.forEach(function (line) {
					if (line !== null) {
						if (line.length * 4 > maxWidth) {
							maxWidth = line.length * 4;
						}
					}
				});

				attrs['.uml-class-' + rect.type + '-text'].text = lines.join('\n');
				attrs['.uml-class-' + rect.type + '-rect'].height = rectHeight;
				attrs['.uml-class-' + rect.type + '-rect'].transform = 'translate(0,' + offsetY + ')';

				offsetY += rectHeight;
			});

			// Now resize the parent SVG to offsetY and the longest string
			var current_size = UMLclass.attributes.size;
			UMLclass.resize(maxWidth, offsetY);
		},

		makeClasses: function (model) {

			var classes = new Array();

			var instantiate = function (UMLclass) {
				var new_class;

				if (UMLclass.isAbstract === "true") {
					new_class = new joint.shapes.uml.Abstract({
						position: UMLclass.position,
						size: UMLclass.position,
						name: [UMLclass.name],
						attributes: JJSdiagram.JJsParse.addAttributes(UMLclass.attributes),
						methods: JJSdiagram.JJsParse.addMethods(UMLclass.methods),
						id: UMLclass.id
					});
				}
				else if (UMLclass.isInterface === "true") {
					new_class = new joint.shapes.uml.Interface({
						position: UMLclass.position,
						size: UMLclass.position,
						name: [UMLclass.name],
						attributes: JJSdiagram.JJsParse.addAttributes(UMLclass.attributes),
						methods: JJSdiagram.JJsParse.addMethods(UMLclass.methods),
						id: UMLclass.id
					});
					// Assign default Interface colours if not already assigned
					// if (UMLclass.displayColor === "") {
					// 	new_class.attr( {'.uml-class-name-rect': { 'stroke': 'black', 'stroke-width': 2, 'fill': '#eed3d3' },
					// 		'.uml-class-attrs-rect': { 'stroke': 'black', 'stroke-width': 2, 'fill': '#ffe3e3' },
					// 		'.uml-class-methods-rect': { 'stroke': 'black', 'stroke-width': 2, 'fill': '#ffe3e3' } });
					// 	UMLclass.displayColor = "assigned";
					// }
				}
				else {
					var hasStateMachine = false;
					if (UMLclass.stateMachines.length > 0) {
						hasStateMachine = true;
					}
					new_class = new joint.shapes.umpleuml.Class({
						position: UMLclass.position,
						size: UMLclass.position,
						name: [UMLclass.name],
						attributes: Page.showAttributes ? JJSdiagram.JJsParse.addAttributes(UMLclass.attributes) : [],
						methods: Page.showMethods ? JJSdiagram.JJsParse.addMethods(UMLclass.methods) : [],
						id: UMLclass.id,
						backgroundColor: UMLclass.displayColor,
						hasStateMachine: hasStateMachine
					});

				}

				// Update all class views to properly fit the contained text strings.
				new_class.attr({
					'.uml-class-attrs-text': { 'font-size': '7pt' },
					'.uml-class-methods-text': { 'font-size': '7pt' }
				});

				classes.push(new_class);
			};

			var findGeneralizations = function (UMLclass) {
				if (UMLclass.extendsClass !== undefined) {
					if (UMLclass.extendsClass !== "") {
						var generalization = {
							source: { id: UMLclass.id },
							target: { id: UMLclass.extendsClass }
						};
						model.generalizationLinks.push(generalization);
					}
				}
			};

			var findInterfaces = function (UMLclass) {
				if (UMLclass.implementedInterfaces !== undefined) {
					if (UMLclass.implementedInterfaces !== []) {
						UMLclass.implementedInterfaces.forEach(function (each) {
							var interfaceLink = {
								source: { id: UMLclass.id },
								target: { id: each.interfacesName }
							};
							model.interfaceLinks.push(interfaceLink);
						});
					}
				}
			};

			var findAbstracts = function (UMLclass) {
				if (UMLclass.isAbstract === "true") {
					// TODO: determine the pattern from an actual example
					console.log(">>>>>>>>>>>>>>> found an ABSTRACT");
				}
			};

			model.umpleClasses.forEach(instantiate);
			model.generalizationLinks = new Array();;
			model.umpleClasses.forEach(findGeneralizations);
			model.interfaceLinks = new Array();;
			model.umpleClasses.forEach(findInterfaces);
			model.umpleClasses.forEach(findAbstracts);

			return classes;
		},

		makeAssociations: function (model) {
			var associations = new Array();

			var instantiate = function (UMLassoc) {
				var new_assoc;

				var assoc_name = UMLassoc.roleOne || UMLassoc.roleTwo;

				if (UMLassoc.isRightComposition === "true" || UMLassoc.isLeftComposition === "true") {
					new_assoc = new joint.shapes.uml.Composition({
						source: { id: (UMLassoc.isRightComposition === "true") ? UMLassoc.classOneId : UMLassoc.classTwoId },
						target: { id: (UMLassoc.isLeftComposition === "true") ? UMLassoc.classOneId : UMLassoc.classTwoId },
						labels: [
							{ position: .5, attrs: { text: { text: assoc_name || '', 'font-size': '8pt' } } },
							{ position: .05, attrs: { text: { text: UMLassoc.multiplicityOne || '', 'font-size': '8pt' }, rect: { 'fill-opacity': '0.6' } } },
							{ position: .95, attrs: { text: { text: UMLassoc.multiplicityTwo || '', 'font-size': '8pt' }, rect: { 'fill-opacity': '0.6' } } }
						],
						vertices: JJSdiagram.setAssociationPathVertices(UMLassoc.classOneId, UMLassoc.classTwoId)
						// [{x: 0,y: 100}, {x: -100, y: 0}]
					});

					new_assoc.attr('.marker-target', { d: 'M 20 5 L 10 10 L 0 5 L 10 0 z', fill: 'black' });
				}
				else {
					new_assoc = new joint.shapes.uml.Association({
						source: { id: UMLassoc.classOneId },
						target: { id: UMLassoc.classTwoId },
						labels: [
							{ position: .5, attrs: { text: { text: assoc_name || '', 'font-size': '8pt' } } },
							{ position: .05, attrs: { text: { text: UMLassoc.multiplicityOne || '', 'font-size': '8pt' }, rect: { 'fill-opacity': '0.6' } } },
							{ position: .95, attrs: { text: { text: UMLassoc.multiplicityTwo || '', 'font-size': '8pt' }, rect: { 'fill-opacity': '0.6' } } }
						],
						vertices: JJSdiagram.setAssociationPathVertices(UMLassoc.classOneId, UMLassoc.classTwoId)
						// [{x: 0,y: 100}, {x: -100, y: 0}]
					});

					new_assoc.set('connector', { name: 'jumpover', args: { type: 'gap' } });

					if (UMLassoc.isLeftNavigable == "true" && UMLassoc.isRightNavigable == "false") {
						new_assoc.attr('.marker-source', { d: 'M 15 0 L 0 7.5 L 15 15', fill: 'white' });
					}
					else if (UMLassoc.isLeftNavigable == "false" && UMLassoc.isRightNavigable == "true") {
						new_assoc.attr('.marker-target', { d: 'M 15 0 L 0 7.5 L 15 15', fill: 'white' });
					}

					// Can get rid of this once the CSS bugs are fixed
					new_assoc.attr({ '.connection-wrap': { fill: 'none' }, '.connection': { fill: 'none' } });
				}

				associations.push(new_assoc);
			};

			model.umpleAssociations.forEach(instantiate);

			return associations;
		},

		makeGeneralizationLinks: function (model) {
			var generalizationLinks = new Array();

			var setPathVertices = function (source, target) {
				var sourceCell = JJSdiagram.paper.model.getCell(source);
				var targetCell = JJSdiagram.paper.model.getCell(target);
				var sourceCellBBox = JJSdiagram.paper.findViewByModel(sourceCell).getBBox();
				var targetCellBBox = JJSdiagram.paper.findViewByModel(targetCell).getBBox();

				var point1 = {
					x: sourceCellBBox.center().x,
					y: targetCellBBox.y + targetCellBBox.height + 25
				};
				var point2 = {
					x: targetCellBBox.center().x,
					y: targetCellBBox.y + targetCellBBox.height + 25
				};

				return [point1, point2];
			}

			var instantiate = function (UMLgeneralization) {
				UMLgeneralization.vertices = setPathVertices(UMLgeneralization.source.id, UMLgeneralization.target.id);
				var new_generalization = new joint.shapes.uml.Generalization(UMLgeneralization)
				new_generalization.set('connector', { name: 'jumpover', args: { type: 'gap' } });
				new_generalization.attr({
					'.connection-wrap': { fill: 'none' },
					'.connection': { fill: 'none' },
					'.marker-target': { d: 'M 10 0 L 0 5 L 10 10 z', fill: 'white' }
				});

				generalizationLinks.push(new_generalization);
			}

			model.generalizationLinks.forEach(instantiate);

			return generalizationLinks;
		},

		makeInterfaceLinks: function (model) {
			var interfaceLinks = new Array();

			var instantiate = function (UMLinterface) {
				var new_interface = new joint.shapes.uml.Implementation(UMLinterface)

				new_interface.attr({
					'.marker-target': { d: 'M 10 0 L 0 5 L 10 10 z', fill: 'white' },
					'.connection': { 'stroke-dasharray': '3,3' }
				});

				interfaceLinks.push(new_interface);
			}

			model.interfaceLinks.forEach(instantiate);

			return interfaceLinks;
		},

		/* 
			To make nested state machines work, there must be a certain order of operations:
			1. Instantiate all sub-state trees (lowest level), and add those states to the graph.
			2. Instantiate all of the transitions within those sub-state trees and add them to the graph.
			3. Perform the auto-layout operation on the current graph (a forest of sub-state-machines).
			4. Add the parent states, level by level, and add them to the graph.
			5. Add all remaining transitions.
			X. Ideally, each additional level of the state machine tree would have an auto-layout operation performed on it. 
		*/

		notYetAddedStatesStack: [],

		notYetAddedTransitionsStack: [],

		parseStateMachine: function (sm) {

			var instantiateState = function (state) {
				var cell;
				var composite = false;

				if (state.isfinal == true) {
					cell = new joint.shapes.uml_state_machine.FinalState({
						attrs: { text: { text: state.name } },
						id: state.name
					});
					JJSdiagram.paper.model.addCell(cell);
				}
				else if (state.stateMachines.length > 0) {
					// Pass each nested machine for parsing.
					state.stateMachines.forEach(JJSdiagram.JJsParse.parseStateMachine);

					// Get the names of all nested states (direct children only).
					var nestedStates = [];
					state.stateMachines.forEach(function (sm) {
						sm.states.forEach(function (s) {
							nestedStates.push(s.name);
						});
					});

					state.nestedStates = nestedStates;

					cell = new joint.shapes.uml_state_machine.CompositeState(state);
					
					//Then push that state's cell onto the stack
					JJSdiagram.JJsParse.notYetAddedStatesStack.push(cell);
					composite = true;
				}
				else {
					cell = new joint.shapes.uml_state_machine.StateNew(state);
					JJSdiagram.paper.model.addCell(cell);
				}

				if (state.isstart == true) {
					// Create a new pseudo-start state
					var ps_name = "pseudo_start_" + state.name;
					var ps = new joint.shapes.uml_state_machine.PseudoStart({ id: ps_name });
					JJSdiagram.paper.model.addCell(ps);

					// Create the transistion from pseudo-start to starting state.
					var link = new joint.shapes.uml_state_machine.Transition({
						source: { id: ps_name },
						target: { id: cell.id },
						attrs: { '.connection-wrap': { fill: 'none' }, '.connection': { fill: 'none' } }
					});
					//jumpover
					link.set('connector', { name: 'jumpover', args: { type: 'gap' } });
					if (!composite) {
						JJSdiagram.paper.model.addCell(link);
					}
					else {
						JJSdiagram.JJsParse.notYetAddedTransitionsStack.push(link);
					}
				}
			};

			var instantiateTransition = function (transition) {
				// Must check whether both source and target are in the graph already.
				var sourceCell = _.find(JJSdiagram.paper.model.getCells(), { "id": transition.source.id });
				var targetCell = _.find(JJSdiagram.paper.model.getCells(), { "id": transition.target.id });

				var link = new joint.shapes.uml_state_machine.Transition(transition);

				//jumpover
				link.set('connector', { name: 'jumpover', args: { type: 'gap' } });
				// Can get rid of this once the CSS bugs are fixed
				link.attr({ '.connection-wrap': { fill: 'none' }, '.connection': { fill: 'none' } });

				//Because the default font-size is 14
				var labels = link.get('labels');
				labels.forEach(function (label) {
					_.extend(label.attrs.text, { 'font-size': 9 });
				});

				if (sourceCell && targetCell) {
					JJSdiagram.paper.model.addCell(link);
				}
				else {
					JJSdiagram.JJsParse.notYetAddedTransitionsStack.push(link);
				}

			};

			//remove all the comments
			var clearActionComments = function (state) {
				for (var actionIndex = 0; actionIndex < state.actions.length; actionIndex++) {
					var commentPos = state.actions[actionIndex].indexOf('//');
					state.actions[actionIndex] = state.actions[actionIndex].slice(0, commentPos);
				}
			}

			sm.states.forEach(clearActionComments);

			sm.states.forEach(instantiateState);

			sm.transitions.forEach(instantiateTransition);
		}
	}
};

