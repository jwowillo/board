.PHONY: doc

all:
	@echo Making board
	@mvn package
	@mv target/board_cli-jar-with-dependencies.jar board_cli.jar
	@mv target/board_gui-jar-with-dependencies.jar board_gui.jar
	@rm -rf target

doc:
	@echo Making doc
	@pandoc doc/requirements.md --latex-engine xelatex \
		-o doc/requirements.pdf
	@pandoc doc/design.md --latex-engine xelatex -o doc/design.pdf
	@rm -rf doc/api
	@mkdir doc/api
	@mvn javadoc:javadoc
	@mv target/site/apidocs/* doc/api
	@rm -rf target
