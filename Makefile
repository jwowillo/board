.PHONY: doc

all:
	@echo Making whiteboard
	@mvn package > /dev/null
	@mv target/whiteboard-1.0-SNAPSHOT.jar .
	@rm -rf target

doc:
	@echo Making doc
	@pandoc doc/requirements.md --latex-engine xelatex \
		-o doc/requirements.pdf
	@rm -rf doc/api
	@mkdir doc/api
	@mvn javadoc:javadoc > /dev/null
	@mv target/site/apidocs/* doc/api
	@rm -rf target
