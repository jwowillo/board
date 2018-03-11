.PHONY: doc

doc:
	@echo Making doc
	@pandoc doc/requirements.md --latex-engine xelatex \
		-o doc/requirements.pdf
	@rm -rf doc/api
	@mkdir doc/api
	@mvn javadoc:javadoc > /dev/null
	@mv target/site/apidocs/* doc/api
	@rm -rf target
