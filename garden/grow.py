#!/usr/bin/python

import fileinput
import json
import os
import pystache
import shutil
from dotmap import DotMap

def render_templates(config):
	renderer = pystache.Renderer()

	for root, dirs, files in os.walk(config.output_dir):
		for filename in [f for f in files if f.endswith(('.xml', '.java', '.kt', '.gradle', '.properties'))]:
			fname = os.path.join(root, filename)

			new_contents = str(renderer.render_path(fname, config))

			with open(fname, 'w') as output_file:
				output_file.write(new_contents)


def create_readme(config):
	f = open(os.path.join(config.output_dir, 'README.md'), 'w')
	f.write(config.app_class_prefix + '\n=========')
	f.close()

def rename_files_and_directories(config):
	for root, dirs, files in os.walk(config.output_dir):
		root_prefix = root + "/"
		for d in dirs:
			full_dir_path = root_prefix + d
			if d == 'WATER_ME_WITH_COMPANY':
				os.renames(full_dir_path, root_prefix + config.company_name)

	for root, dirs, files in os.walk(config.output_dir):
		root_prefix = root + "/"
		for d in dirs:
			full_dir_path = root_prefix + d
			if d == 'WATER_ME_WITH_APP_NAME':
				os.renames(full_dir_path, root_prefix + config.app_package_name_prefix)

	for root, dirs, files in os.walk(config.output_dir):
			root_prefix = root + "/"
			for f in files:
				if 'WATER_ME_WITH_CLASS_PREFIX_' in f:
					os.renames(root_prefix + f, root_prefix + f.replace('WATER_ME_WITH_CLASS_PREFIX_', config.app_class_prefix))

def copy_template_to_output_dir(root_template_folder, config):
	shutil.copytree(root_template_folder, config.output_dir)

def validate_config(config):
	if 'language' in config:
		if config.language != 'java' and config.language != 'kotlin':
			raise Exception("Unknown language " + config.language)
	else:
		config['language'] = 'java'

	required_fields = [
		'output_dir', 'company_name', 'app_package_name_prefix',
		'app_class_prefix', 'application_id', 'compile_sdk_version',
		'build_tools_version', 'min_sdk_version', 'target_sdk_version'
		]

	for req_field in required_fields:
		if req_field not in config:
			raise Exception("Missing required field: " + req_field)

	config['app_class_prefix_lowercase'] =  config.app_class_prefix[0].lower() + config.app_class_prefix[1:]

	return config

def grow(config_dict):
	config = validate_config(DotMap(config_dict))
	seed_template_folder = os.path.dirname(os.path.realpath(__file__)) + "/seeds/" + config.language
	
	copy_template_to_output_dir(seed_template_folder, config)
	rename_files_and_directories(config)
	create_readme(config)
	render_templates(config)

def main():
	json_data = json.loads(''.join(fileinput.input()))
	grow(json_data)
	
if __name__ == '__main__':
	main()